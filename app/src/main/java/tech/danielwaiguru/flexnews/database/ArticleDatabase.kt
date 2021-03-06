package tech.danielwaiguru.flexnews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.danielwaiguru.flexnews.models.Article

@TypeConverters(Converters::class)
@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
/*
    companion object{
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK)
        {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articles.db"
            ).build()
    }*/
}