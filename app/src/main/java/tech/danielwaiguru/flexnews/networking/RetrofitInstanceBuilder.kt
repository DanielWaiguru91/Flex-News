package tech.danielwaiguru.flexnews.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
private fun buildRetrofit(): Retrofit{
    val contentTypes = "application/json".toMediaType()
    return Retrofit.Builder()
        .client(buildClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentTypes))
        .build()
}
/**
 * NewsApiService builder
 */
fun buildNewsApiService(): NewsApiService =
    buildRetrofit().create(NewsApiService::class.java)