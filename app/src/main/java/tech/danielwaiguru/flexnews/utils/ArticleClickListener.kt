package tech.danielwaiguru.flexnews.utils

import tech.danielwaiguru.flexnews.models.Article

interface ArticleClickListener {
    fun onArticleItemClicked(article: Article)
}