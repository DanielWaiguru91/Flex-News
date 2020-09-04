package tech.danielwaiguru.flexnews.repositories

import tech.danielwaiguru.flexnews.networking.RemoteNewsApi

class NewsRepository(private val remoteNewsApi: RemoteNewsApi) {
    suspend fun getTrendingNews(pageNumber: Int)  = remoteNewsApi.getTrendingNews(pageNumber)
}