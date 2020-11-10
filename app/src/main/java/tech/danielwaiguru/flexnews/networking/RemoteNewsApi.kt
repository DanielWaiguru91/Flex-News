package tech.danielwaiguru.flexnews.networking

import android.util.Log
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.models.Failure
import tech.danielwaiguru.flexnews.models.Result
import tech.danielwaiguru.flexnews.models.Success

class RemoteNewsApi (private val newsApiService: NewsApiService){
    suspend fun getTrendingNews(pageNumber: Int): Result<List<Article>> = try {
        val response = newsApiService.trendingNews(pageNum = pageNumber).articles
        Success(response)
    }
    catch (error: Throwable){
        Log.d("api", error.toString())
        Failure("Server error encountered")
    }
}
