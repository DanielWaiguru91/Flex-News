package tech.danielwaiguru.flexnews.networking

import androidx.paging.PagingSource
import okio.IOException
import retrofit2.HttpException
import tech.danielwaiguru.flexnews.common.Constants.PAGE_INDEX
import tech.danielwaiguru.flexnews.models.Article
import javax.inject.Inject

class NewsDataSource(private val query: String):
    PagingSource<Int, Article>() {
    @Inject
    lateinit var newsApiService: NewsApiService
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGE_INDEX
        return try {
            val response = newsApiService
                .searchNews(query = query, pageNum = position).articles
            LoadResult.Page(
                data = response,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey =  if (response.isEmpty()) null else position + 1
            )
        }
        catch (exception: IOException){
            LoadResult.Error(exception)
        }
        catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}