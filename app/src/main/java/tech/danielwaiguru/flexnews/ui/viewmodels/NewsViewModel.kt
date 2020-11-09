package tech.danielwaiguru.flexnews.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.repositories.NewsRepository

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel(){
    private val _toast: MutableLiveData<String?> = MutableLiveData()
    val toast : LiveData<String?>
    get() = _toast
    fun fetchNews(): Flow<PagingData<Article>> {
        return newsRepository.trendingNews().cachedIn(viewModelScope)
    }
}