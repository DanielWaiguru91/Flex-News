package tech.danielwaiguru.flexnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.ui.MainActivity
import tech.danielwaiguru.flexnews.ui.NewsViewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).newsViewModel
    }

}
