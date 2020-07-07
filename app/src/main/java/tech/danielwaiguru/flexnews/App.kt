package tech.danielwaiguru.flexnews

import android.app.Application
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi
import tech.danielwaiguru.flexnews.networking.buildNewsApiService

class App: Application() {
    companion object{
        private lateinit var instance: App
        private val newsApiService by lazy { buildNewsApiService() }
        val remoteNewsApi by lazy { RemoteNewsApi(newsApiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}