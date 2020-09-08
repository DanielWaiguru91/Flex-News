package tech.danielwaiguru.flexnews.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.repositories.NewsRepository

class FavoriteNewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.saveArticle(article)
    }
}