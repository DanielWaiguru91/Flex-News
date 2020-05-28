package tech.danielwaiguru.flexnews.data

import tech.danielwaiguru.flexnews.data.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)