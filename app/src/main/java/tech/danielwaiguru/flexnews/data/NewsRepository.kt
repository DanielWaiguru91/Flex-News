package tech.danielwaiguru.flexnews.data

import tech.danielwaiguru.flexnews.App


class NewsRepository(articleDatabase: ArticleDatabase) {
    val remoteNewsApi = App.remoteNewsApi
    suspend fun trendingNews(countryCode:String, pageNum: Int)
        remoteNewsApi.trendingNews(countryCode, pageNum)
}