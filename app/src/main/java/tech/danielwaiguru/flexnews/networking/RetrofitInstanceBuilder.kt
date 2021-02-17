package tech.danielwaiguru.flexnews.networking


import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
        //.baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}
/**
 * NewsApiService builder
 */
fun buildNewsApiService(): NewsApiService =
    buildRetrofit().create(NewsApiService::class.java)