package tech.danielwaiguru.flexnews.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import tech.danielwaiguru.flexnews.common.Constants
import tech.danielwaiguru.flexnews.common.Constants.MAX_SIZE
import tech.danielwaiguru.flexnews.database.ArticleDao
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NewsApiService
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi
import tech.danielwaiguru.flexnews.paging.NewsPagingSource

class NewsRepository(
    private val remoteNewsApi: RemoteNewsApi,
    private val localDataSource: ArticleDao,
    private val apiService: NewsApiService) {
    fun getFavArticles() = localDataSource.getNewsArticles()
    suspend fun getTrendingNews(pageNumber: Int)  = remoteNewsApi.getTrendingNews(pageNumber)
    suspend fun saveArticle(article: Article) = localDataSource.insertNewsArticle(article)
    fun searchNews(query: String) =
       Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                maxSize = MAX_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(apiService, query) }
        ).flow

}