package presentation.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_news_app.R
import presentation.favorites.FavouriteManager
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var newsRecycler: RecyclerView

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(NewsApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        newsRecycler = findViewById(R.id.newsRecycler)
        newsRecycler.layoutManager = LinearLayoutManager(this)

        fetchNews()
    }

    private fun fetchNews() {
        api.getTopHeadlines().enqueue(object : Callback<NewsResponse> {

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val articles = response.body()!!.articles
                    android.util.Log.d("API", "Articles count: ${articles.size}")
                    newsRecycler.adapter = NewsAdapter(articles)
                } else {
                    android.util.Log.e(
                        "API",
                        "Error ${response.code()} : ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                android.util.Log.e("API", "Failed", t)
            }
        })
    }

    inner class NewsAdapter(private val articles: List<Article>) :
        RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        inner class NewsViewHolder(view: android.view.View) :
            RecyclerView.ViewHolder(view) {
            val img = view.findViewById<ImageView>(R.id.imgNews)
            val title = view.findViewById<android.widget.TextView>(R.id.tvTitle)
            val desc = view.findViewById<android.widget.TextView>(R.id.tvDescription)
            val fav = view.findViewById<ImageView>(R.id.imgFav)
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): NewsViewHolder {
            val view = layoutInflater.inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val article = articles[position]

            // Text binding with default values
            holder.title.text = article.title ?: "No title available"
            holder.desc.text = article.description ?: "No description"

            // Glide without KAPT, with placeholder
            Glide.with(holder.img.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder) // optional placeholder
                .error(R.drawable.placeholder) // fallback if image fails
                .into(holder.img)

            // Favourite logic
            val favList = FavouriteManager.getFavourites(this@HomeActivity)
            val isFav = favList.any { it.title == article.title }

            holder.fav.setImageResource(
                if (isFav) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )

            holder.fav.setOnClickListener {
                FavouriteManager.addToFavourite(this@HomeActivity, article)
                holder.fav.setImageResource(R.drawable.ic_favorite)
                holder.fav.isEnabled = false // prevent double-click
            }

            // Navigate to details screen
            holder.itemView.setOnClickListener {
                val intent = Intent(this@HomeActivity, presentation.details.detailsActivity::class.java)
                intent.putExtra("title", article.title)
                intent.putExtra("subtitle", article.source?.name ?: "")
                intent.putExtra("description", article.description)
                intent.putExtra("image", article.urlToImage)
                startActivity(intent)
            }
        }

        override fun getItemCount(): Int = articles.size
    }
}
