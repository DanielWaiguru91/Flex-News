package tech.danielwaiguru.flexnews.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import tech.danielwaiguru.flexnews.database.Converters
import java.io.Serializable

@Entity(tableName = "articles")
@TypeConverters(Converters::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var articleId: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable