package tech.danielwaiguru.flexnews.ui

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

class NewsViewModel(private val newsRepository: NewsRepository, application: Application) :
    AndroidViewModel(application){
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(application.getSystemService(ConnectivityManager::class.java)!!)
    }
    val trendingNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private val page = 1
    val searchedNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private val searchPage = 1
    init {
        getTrendingNews("us")
    }
    private fun getTrendingNews(countryCode: String) = viewModelScope.launch {
        trendingNews.postValue(Result.Loading())
        networkStatusChecker.performIfConnectedToInternet {
            val response = newsRepository.trendingNews(countryCode, page)
            trendingNews.postValue(trendingNewsResponse(response))
        }
    }
    private fun trendingNewsResponse(response: Response<NewsResponse>): Result<NewsResponse> {
       if (response.isSuccessful){
           response.body()?.let {
               return Result.Success(it)
           }
       }
        return Result.Failure(response.message())
    }
    private fun searchNews(searchTerm: String) = viewModelScope.launch {
        networkStatusChecker.performIfConnectedToInternet {
            val response = newsRepository.searchNews(searchTerm, searchPage)
            searchedNews.postValue(searchedArticle(response))
        }
    }
    private fun searchedArticle(response: Response<NewsResponse>): Result<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Failure(response.message())
    }
}