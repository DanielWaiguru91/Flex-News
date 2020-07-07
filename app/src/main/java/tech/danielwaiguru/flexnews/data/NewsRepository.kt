package tech.danielwaiguru.flexnews.data



class NewsRepository(articleDatabase: ArticleDatabase) {
    suspend fun trendingNews(countryCode:String, pageNum: Int)
        .newsApi.trendingNews(countryCode, pageNum)
}