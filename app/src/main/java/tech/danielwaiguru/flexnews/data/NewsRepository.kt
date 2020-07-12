package tech.danielwaiguru.flexnews.data

import tech.danielwaiguru.flexnews.networking.BuildNewsApiService


class NewsRepository() {
    private val remoteNewsApi = BuildNewsApiService.newsApi
    suspend fun trendingNews(countryCode: String, pageNum: Int) =
        remoteNewsApi.trendingNews(countryCode, pageNum)

    suspend fun searchNews(searchTerm: String, pageNum: Int) =
        remoteNewsApi.searchNews(searchTerm, pageNum)

}