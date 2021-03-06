package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.ui.viewmodels.FavoriteNewsViewModel
@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private val favoriteNewsViewModel: FavoriteNewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadArticle()
        initListeners()
    }
    private fun loadArticle(){
        arguments?.let {
            val article = ArticleFragmentArgs.fromBundle(it).article
                webView.apply {
                    webViewClient = WebViewClient()
                    loadUrl(article.url)
                }
        }
    }
    private fun initListeners() {
        favoriteFab.setOnClickListener { saveArticle() }
    }
    private fun saveArticle() {
        arguments?.let {
            val article = ArticleFragmentArgs.fromBundle(it).article
            favoriteNewsViewModel.saveArticle(article)
        }
    }
}
