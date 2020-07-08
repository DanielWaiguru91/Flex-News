package tech.danielwaiguru.flexnews.networking

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tech.danielwaiguru.flexnews.models.response.NewsResponse

/**
 * Service interface
 */
interface NewsApiService {
    companion object {
        const val API_KEY = "10ccffe776b14ac4829aab687f590fee"
        const val BASE_URL = "https://newsapi.org"
    }
    @GET("v2/top-headlines")
    suspend fun trendingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNum: Int = 1,
        @Query("api_key")
        api_key: String = API_KEY
    ):Response<NewsResponse>

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