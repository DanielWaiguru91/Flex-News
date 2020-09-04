package tech.danielwaiguru.flexnews

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi
import tech.danielwaiguru.flexnews.networking.buildNewsApiService

@HiltAndroidApp
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
