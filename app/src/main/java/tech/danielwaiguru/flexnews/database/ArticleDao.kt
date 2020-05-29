package tech.danielwaiguru.flexnews.database

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.danielwaiguru.flexnews.data.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(article: Article)
    @Query("SELECT * FROM articles")
    fun getNewsArticles(): LiveData<List<Article>>
    @Delete
    suspend fun deleteNewsArticle(article: Article)
}