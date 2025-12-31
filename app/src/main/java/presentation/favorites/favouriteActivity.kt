package presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android_news_app.R

class favouriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        val container = findViewById<LinearLayout>(R.id.favContainer)
        val favList = FavouriteManager.getFavourites(this)

        for (article in favList) {
            val view = LayoutInflater.from(this)
                .inflate(R.layout.item_news, container, false)

            view.findViewById<TextView>(R.id.tvTitle).text = article.title
            view.findViewById<TextView>(R.id.tvDescription).text = article.description ?: ""

            Glide.with(this)
                .load(article.urlToImage)
                .into(view.findViewById<ImageView>(R.id.imgNews))

            container.addView(view)
        }
    }
}
