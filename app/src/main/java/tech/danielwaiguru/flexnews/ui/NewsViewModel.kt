package tech.danielwaiguru.flexnews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tech.danielwaiguru.flexnews.data.NewsRepository
import tech.danielwaiguru.flexnews.models.response.NewsResponse
import tech.danielwaiguru.flexnews.utility.Resource

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel(){
    val trendingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val page = 1
    init {
        getTrendingNews("ke")
    }
    private fun getTrendingNews(countryCode: String) = viewModelScope.launch {
        trendingNews.postValue(Resource.Loading())
        val response = newsRepository.trendingNews(countryCode, page)
        trendingNews.postValue(trendingNewsResponse(response))
    }
    private fun trendingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
       if (response.isSuccessful){
           response.body()?.let {
               return Resource.Success(it)
           }
       }
        return Resource.Error(response.message())
    }
}