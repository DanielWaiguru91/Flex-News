package tech.danielwaiguru.flexnews.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.models.Failure
import tech.danielwaiguru.flexnews.models.Success
import tech.danielwaiguru.flexnews.repositories.NewsRepository

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel(){
    private val _trendingNews: MutableLiveData<List<Article>> = MutableLiveData()
    val trendingNews: LiveData<List<Article>>
    get() = _trendingNews
    private val _toast: MutableLiveData<String?> = MutableLiveData()
    val toast : LiveData<String?>
    get() = _toast
    var currentPage = 1
    fun fetchTrendingNews() = viewModelScope.launch {
        val result = newsRepository.getTrendingNews(currentPage)
        currentPage ++
        when (result) {
            is Success ->{
                _trendingNews.value = result.data
            }
            is Failure -> {
                _toast.value = result.error.toString()
            }
        }

    }
}