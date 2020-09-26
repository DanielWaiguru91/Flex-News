package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.SearchNewsAdapter
import tech.danielwaiguru.flexnews.ui.viewmodels.SearchNewsViewModel

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {
    private val searchViewModel by viewModels<SearchNewsViewModel>()
    private val searchAdapter: SearchNewsAdapter by lazy {
        SearchNewsAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var job :Job? = null
        etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(300L)
                editable?.let {
                    if (it.toString().isNotEmpty()){

                    }
                }
            }
        }
        searchViewModel.searchedNews.observe(viewLifecycleOwner, {
            searchAdapter.submitList(it)
        })
    }

}
