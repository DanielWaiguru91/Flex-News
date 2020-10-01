package tech.danielwaiguru.flexnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import tech.danielwaiguru.flexnews.database.ArticleDao
import tech.danielwaiguru.flexnews.networking.NewsApiService
import tech.danielwaiguru.flexnews.networking.RemoteNewsApi
import tech.danielwaiguru.flexnews.repositories.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteNewsApi: RemoteNewsApi, articleDao: ArticleDao, apiService: NewsApiService):
            NewsRepository = NewsRepository(remoteNewsApi, articleDao, apiService)
}