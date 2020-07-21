package tech.danielwaiguru.flexnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.ui.main.MainActivity
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoriteNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).newsViewModel
    }
}
