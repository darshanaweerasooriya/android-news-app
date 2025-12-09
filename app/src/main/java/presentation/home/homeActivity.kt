package presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_news_app.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class homeActivity : AppCompatActivity() {
  private val retrofit = Retrofit.Builder()
      .baseUrl("https://newsapi.org/v2/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    private val api = retrofit.create(NewsApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        fetchNews()
    }

    private fun fetchNews(){
        api.getTopHeadlines().enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(
                call: retrofit2.Call<NewsResponse>,
                response: retrofit2.Response<NewsResponse>
            ){
                val data = response.body()

                if(data != null){
                    Log.d("NEWS", "Fetched: ${data.articles.size}")
                }
            }

            override  fun  onFailure(call: retrofit2.Call<NewsResponse>, t: Throwable){
                t.printStackTrace()
            }
        })
    }


}