package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class ArticleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }
}
