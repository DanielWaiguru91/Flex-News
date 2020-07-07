package tech.danielwaiguru.flexnews.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.flexnews.api.NewsService.Companion.BASE_URL

/*
object RetrofitInstance {
    val newsApi: NewsService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(NewsService::class.java)
    }
}*/
/**
 * Build instance of OkHttpClient
 */
fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()

/**
 *Retrofit client factory
 */
fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}