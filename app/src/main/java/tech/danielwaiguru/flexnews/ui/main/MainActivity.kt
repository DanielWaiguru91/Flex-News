package tech.danielwaiguru.flexnews.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.repositories.NewsRepository
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}
