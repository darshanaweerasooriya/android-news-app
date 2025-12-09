package presentation.home

import android.icu.text.CaseMap.Title
import android.media.Image

data class NewsResponse(
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?
)