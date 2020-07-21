package tech.danielwaiguru.flexnews.models

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: Int,
    val name: String
)