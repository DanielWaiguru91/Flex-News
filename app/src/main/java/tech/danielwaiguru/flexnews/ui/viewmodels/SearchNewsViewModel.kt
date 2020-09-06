package tech.danielwaiguru.flexnews.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.repositories.NewsSearchRepository

class SearchNewsViewModel
@ViewModelInject constructor(private val repository: NewsSearchRepository): ViewModel() {
    private var currentQueryValue : String? = null
    private var currentSearchResult: Flow<PagingData<Article>>? = null
    fun searchNews(query: String): Flow<PagingData<Article>>{
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null){
            return lastResult
        }
        currentQueryValue = query
        val newResult = repository.searchNews(query).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}