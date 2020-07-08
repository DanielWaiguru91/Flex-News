package tech.danielwaiguru.flexnews

import android.app.Application
import android.content.Context
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi
import tech.danielwaiguru.flexnews.networking.buildNewsApiService

private const val KEY_PREFS = "KEY_PREFS"
private const val API_KEY = "API_KEY"
class App: Application() {
    companion object{
        private lateinit var instance: App
        private val sharedPrefs by lazy {
            instance.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)
        }
        fun saveKey(apiKey: String){
            sharedPrefs.edit().putString(API_KEY, apiKey).apply()
        }
        fun getKey() = sharedPrefs.getString(API_KEY, "") ?: ""
        private val newsApiService by lazy { buildNewsApiService() }
        val remoteNewsApi by lazy { RemoteNewsApi(newsApiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}