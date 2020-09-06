package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article.*
import tech.danielwaiguru.flexnews.R

class ArticleFragment : Fragment() {
    //private val args: ArticleFragmentArgs by navArgs()
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
}
