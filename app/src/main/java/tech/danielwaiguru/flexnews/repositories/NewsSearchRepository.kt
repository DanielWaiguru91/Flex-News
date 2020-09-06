package tech.danielwaiguru.flexnews.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.danielwaiguru.flexnews.common.Constants.NETWORK_PAGE_SIZE
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NewsDataSource

class NewsSearchRepository {
    fun searchNews(query: String): Flow<PagingData<Article>>{
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NewsDataSource(query) }
        ).flow
    }
}