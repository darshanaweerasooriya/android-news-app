package presentation.home


data class NewsResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article> = emptyList()
)
data class Article(
    val source: Source?,
    val title: String?,
    val description: String?,
    val urlToImage: String?
)


data class Source(
    val id: String?,
    val name: String?
)