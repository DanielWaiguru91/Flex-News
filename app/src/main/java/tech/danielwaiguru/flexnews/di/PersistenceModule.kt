package tech.danielwaiguru.flexnews.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import tech.danielwaiguru.flexnews.common.Constants.DB_NAME
import tech.danielwaiguru.flexnews.database.ArticleDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(context, ArticleDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun provideNewsDao(database: ArticleDatabase) =
        database.getArticleDao()

}