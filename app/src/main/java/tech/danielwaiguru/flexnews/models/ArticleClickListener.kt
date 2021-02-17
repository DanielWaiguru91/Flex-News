package tech.danielwaiguru.flexnews.models

data class ArticleClickListener (
    val clickListener:(article: Article) -> Unit
)