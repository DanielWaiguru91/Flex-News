package tech.danielwaiguru.flexnews.models.response

import com.squareup.moshi.Json
import tech.danielwaiguru.flexnews.models.Article

data class NewsResponse(
    @field:Json(name = "articles") val articles: List<Article>,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "totalResults") val totalResults: Int
)