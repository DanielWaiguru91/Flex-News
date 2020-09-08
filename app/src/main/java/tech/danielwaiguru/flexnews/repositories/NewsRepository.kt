package tech.danielwaiguru.flexnews.repositories

import tech.danielwaiguru.flexnews.database.ArticleDao
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi

class NewsRepository(
    private val remoteNewsApi: RemoteNewsApi, private val localDataSource: ArticleDao) {
    suspend fun getTrendingNews(pageNumber: Int)  = remoteNewsApi.getTrendingNews(pageNumber)
    suspend fun saveArticle(article: Article) = localDataSource.insertNewsArticle(article)
}