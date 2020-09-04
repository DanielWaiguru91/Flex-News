package tech.danielwaiguru.flexnews.viewmodels

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.danielwaiguru.flexnews.data.NewsRepository
import tech.danielwaiguru.flexnews.models.response.NewsResponse
import tech.danielwaiguru.flexnews.networking.NetworkStatusChecker
import tech.danielwaiguru.flexnews.models.Result
import tech.danielwaiguru.flexnews.models.Success

class NewsViewModel(private val newsRepository: NewsRepository, application: Application) :
    AndroidViewModel(application){
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(application.getSystemService(ConnectivityManager::class.java)!!)
    }
    val trendingNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private val page = 1
    val searchedNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()

}