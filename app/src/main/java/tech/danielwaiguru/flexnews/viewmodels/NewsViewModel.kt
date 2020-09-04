package tech.danielwaiguru.flexnews.viewmodels

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tech.danielwaiguru.flexnews.repositories.NewsRepository
import tech.danielwaiguru.flexnews.models.response.NewsResponse
import tech.danielwaiguru.flexnews.networking.NetworkStatusChecker
import tech.danielwaiguru.flexnews.models.Result

class NewsViewModel(private val newsRepository: NewsRepository, application: Application) :
    AndroidViewModel(application){
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(application.getSystemService(ConnectivityManager::class.java)!!)
    }
    val trendingNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private val page = 1
    val searchedNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()

}