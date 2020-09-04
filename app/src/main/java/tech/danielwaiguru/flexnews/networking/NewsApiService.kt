package tech.danielwaiguru.flexnews.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tech.danielwaiguru.flexnews.BuildConfig
import tech.danielwaiguru.flexnews.models.response.NewsResponse

/**
 * Service interface
 */
interface NewsApiService {
    companion object {
        const val API_KEY = BuildConfig.key
    }
    @GET("/v2/top-headlines")
    suspend fun trendingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNum: Int,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):NewsResponse

    /**
     * News search
     */
    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")
        queryEveryThing :String,
        @Query("page")
        pageNum: Int = 1,
        @Query("api_key")
        api_key: String = API_KEY
    ): Response<NewsResponse>
}