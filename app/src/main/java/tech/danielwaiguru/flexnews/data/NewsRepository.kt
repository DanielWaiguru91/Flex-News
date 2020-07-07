package tech.danielwaiguru.flexnews.data

import tech.danielwaiguru.flexnews.api.RetrofitInstance

class NewsRepository(articleDatabase: ArticleDatabase) {
    suspend fun trendingNews(countryCode:String, pageNum: Int) =
        RetrofitInstance.newsApi.trendingNews(countryCode, pageNum)
}