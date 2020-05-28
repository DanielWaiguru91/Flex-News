package tech.danielwaiguru.flexnews.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.danielwaiguru.flexnews.api.NewsService.Companion.BASE_URL

object RetrofitInstance {
    val newsApi: NewsService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(NewsService::class.java)
    }
}