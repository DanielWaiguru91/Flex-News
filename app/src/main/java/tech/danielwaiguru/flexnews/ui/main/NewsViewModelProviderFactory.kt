package tech.danielwaiguru.flexnews.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.danielwaiguru.flexnews.repositories.NewsRepository
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

class NewsViewModelProviderFactory(private val newsRepository: NewsRepository,
                                   private val application: Application):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            newsRepository,
            application
        ) as T
    }
}