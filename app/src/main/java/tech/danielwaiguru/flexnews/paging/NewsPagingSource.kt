package tech.danielwaiguru.flexnews.paging

import androidx.paging.PagingSource
import okio.IOException
import retrofit2.HttpException
import tech.danielwaiguru.flexnews.common.Constants.PAGE_INDEX
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NewsApiService

class NewsPagingSource(private val apiService: NewsApiService, private val query: String):
    PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGE_INDEX
        return try {
            val response = apiService.trendingNews(pageNum = position).articles
            LoadResult.Page(
                data = response,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        }
        catch (e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}