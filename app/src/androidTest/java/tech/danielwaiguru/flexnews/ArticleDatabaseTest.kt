package tech.danielwaiguru.flexnews

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import tech.danielwaiguru.flexnews.data.ArticleDao
import tech.danielwaiguru.flexnews.data.ArticleDatabase
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.models.Source

@RunWith(AndroidJUnit4::class)
class ArticleDatabaseTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private val articleDatabase: ArticleDatabase? = null
    private var articleDao: ArticleDao? = null

    @Before
    fun setup(){
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val articleDatabase = Room.inMemoryDatabaseBuilder(
            context,
            ArticleDatabase::class.java)
            .allowMainThreadQueries().build()
        articleDao = articleDatabase.getArticleDao()
    }

    @After
    fun closeConn(){
        articleDatabase?.close()
    }

    @Test
    suspend fun crudOperation(){
        val preRetrieve = articleDao?.getNewsArticles()
        val data = Article(1,"Daniel", "Android development technologies", "learn developing android apps",
        "3rd June 2020", Source(1,"Source 1"), "Android engineering", "https://danielwaiguru.tech",
        "www.unsplash.com")
        articleDao?.insertNewsArticle(data)
        val postRetrieve = articleDao?.getNewsArticles()

        //val diff = postRetrieve?.minus(preRetrieve)
    }

}