package tech.danielwaiguru.flexnews.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.danielwaiguru.flexnews.common.Constants
import tech.danielwaiguru.flexnews.database.ArticleDao
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NewsDataSource
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi

class NewsRepository(
    private val remoteNewsApi: RemoteNewsApi, private val localDataSource: ArticleDao) {
    fun getFavArticles() = localDataSource.getNewsArticles()
    suspend fun getTrendingNews(pageNumber: Int)  = remoteNewsApi.getTrendingNews(pageNumber)
    suspend fun saveArticle(article: Article) = localDataSource.insertNewsArticle(article)
    fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsDataSource(query) }
        ).flow
    }
}