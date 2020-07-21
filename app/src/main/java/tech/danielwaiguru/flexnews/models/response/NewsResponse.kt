package tech.danielwaiguru.flexnews.models.response

import kotlinx.serialization.Serializable
import tech.danielwaiguru.flexnews.models.Article

@Serializable
data class NewsResponse(
    val articles: List<Article> = mutableListOf()
)