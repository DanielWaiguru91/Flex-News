package tech.danielwaiguru.flexnews.data

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.danielwaiguru.flexnews.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(article: Article)
    @Query("SELECT * FROM articles")
    fun getNewsArticles(): LiveData<List<Article>>
    @Delete
    suspend fun deleteNewsArticle(article: Article)
}