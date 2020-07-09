package tech.danielwaiguru.flexnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.data.NewsRepository

class MainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    private lateinit var viewModelProviderFactory: NewsViewModelProviderFactory
    private lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRepository = NewsRepository()
        viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository, application)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}
