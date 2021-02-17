package tech.danielwaiguru.flexnews.models.response


import tech.danielwaiguru.flexnews.models.Article

data class NewsResponse(
    val articles: List<Article> = mutableListOf()
)