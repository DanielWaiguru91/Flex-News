package tech.danielwaiguru.flexnews.models

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @ContextualSerialization
    val id: Any,
    val name: String
)