package tech.danielwaiguru.flexnews.models


sealed class Result<out T: Any>

data class Success<out T: Any>(val data: List<Article>): Result<T>()

data class Failure(val error: String): Result<Nothing>()