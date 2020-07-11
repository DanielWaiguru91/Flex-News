package tech.danielwaiguru.flexnews.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.flexnews.networking.NewsApiService.Companion.BASE_URL

/**
 * Build instance of OkHttpClient
 */
private val buildClient: OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

/**
 *Retrofit client factory
 */
fun buildRetrofit(): Retrofit{
    return Retrofit.Builder()
        .client(buildClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}
object BuildNewsApiService {
    val newsApi: NewsApiService by lazy {
        buildRetrofit().create(NewsApiService::class.java)
    }
}