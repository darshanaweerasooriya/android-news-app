package presentation.home


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "81f1b56f0e784411a2f52bbe8ca26f77"

    ): Call<NewsResponse>
}