package tech.danielwaiguru.flexnews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.danielwaiguru.flexnews.data.NewsRepository
import tech.danielwaiguru.flexnews.models.response.NewsResponse
import tech.danielwaiguru.flexnews.utility.Result

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel(){
    val trendingNews: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private val page = 1
    init {
        getTrendingNews("us")
    }
    private fun getTrendingNews(countryCode: String) = viewModelScope.launch {
        trendingNews.postValue(Result.Loading())
        val response = newsRepository.trendingNews(countryCode, page)
        trendingNews.postValue(trendingNewsResponse(response))
    }
    private fun trendingNewsResponse(response: Response<NewsResponse>): Result<NewsResponse> {
       if (response.isSuccessful){
           response.body()?.let {
               return Result.Success(it)
           }
       }
        return Result.Failure(response.message())
    }
}