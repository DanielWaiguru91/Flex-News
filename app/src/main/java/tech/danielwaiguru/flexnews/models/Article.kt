package tech.danielwaiguru.flexnews.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import tech.danielwaiguru.flexnews.database.Converters

@Entity(tableName = "article")
@TypeConverters(Converters::class)
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var articleId: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable