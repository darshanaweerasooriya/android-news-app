package presentation.favorites

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import presentation.home.Article

object FavouriteManager {

    private const val PREF_NAME = "fav_news"
    private const val KEY_FAV = "favorites"

    fun addToFavourite(context: Context, article: Article) {
        val list = getFavourites(context).toMutableList()
        if (!list.any { it.title == article.title }) {
            list.add(article)
            save(context, list)
        }
    }

    fun getFavourites(context: Context): List<Article> {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = pref.getString(KEY_FAV, null) ?: return emptyList()

        val type = object : TypeToken<List<Article>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun save(context: Context, list: List<Article>) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        pref.edit().putString(KEY_FAV, Gson().toJson(list)).apply()
    }
}
