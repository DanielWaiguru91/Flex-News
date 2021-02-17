package tech.danielwaiguru.flexnews.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.danielwaiguru.flexnews.common.Constants.MAX_SIZE
import tech.danielwaiguru.flexnews.common.Constants.NETWORK_PAGE_SIZE
import tech.danielwaiguru.flexnews.database.ArticleDao
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NewsApiService
import tech.danielwaiguru.flexnews.paging.NewsPagingSource
import tech.danielwaiguru.flexnews.paging.SearchPagingSource

class NewsRepository(
    private val localDataSource: ArticleDao,
    private val apiService: NewsApiService) {
    fun getFavArticles() = localDataSource.getNewsArticles()
    suspend fun saveArticle(article: Article) = localDataSource.insertNewsArticle(article)
    fun searchNews(query: String) =
       Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = MAX_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).flow

    fun trendingNews(): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = MAX_SIZE, enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(apiService)}
        ).flow
}