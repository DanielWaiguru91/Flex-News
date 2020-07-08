package tech.danielwaiguru.flexnews.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.flexnews.App
import tech.danielwaiguru.flexnews.networking.NewsApiService.Companion.BASE_URL

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
private const val AUTHORIZATION_HEADER = "AUTHORIZATION_HEADER"
/**
 * Build instance of OkHttpClient
 */
fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(buildAuthorizationInterceptor())
        .build()

fun buildAuthorizationInterceptor() = object : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (App.getKey().isBlank()) return chain.proceed(originalRequest)

        val newRequest = originalRequest.newBuilder()
            .addHeader(AUTHORIZATION_HEADER, App.getKey())
            .build()
        return chain.proceed(newRequest)
    }
}

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
fun buildNewsApiService(): NewsApiService =
    buildRetrofit().create(NewsApiService::class.java)