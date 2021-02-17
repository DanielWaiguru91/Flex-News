package tech.danielwaiguru.flexnews.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import tech.danielwaiguru.flexnews.repositories.NewsRepository

class SearchNewsViewModel
@ViewModelInject constructor(private val repository: NewsRepository): ViewModel() {
    private val queryValue = MutableLiveData<String>()
    //private var currentQueryValue : String? = null
    //private var currentSearchResult: Flow<PagingData<Article>>? = null
    /*fun searchNews(query: String): Flow<PagingData<Article>>{
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null){
            return lastResult
        }
        currentQueryValue = query
        val newResult = repository.searchNews(query).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }*/
    fun setQuery(query: String){
        queryValue.value = query
    }
    val searchedNews = queryValue.switchMap {
        repository.searchNews(it).cachedIn(viewModelScope).asLiveData(Dispatchers.Main)
    }
}