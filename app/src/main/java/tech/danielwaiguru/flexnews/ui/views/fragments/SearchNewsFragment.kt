package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.SearchAdapter
import tech.danielwaiguru.flexnews.ui.viewmodels.SearchNewsViewModel

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {
    private val searchViewModel by viewModels<SearchNewsViewModel>()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }
    private var searchJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchJob?.cancel()
        searchViewModel.searchedNews.observe(viewLifecycleOwner, { pagingData ->
            searchJob = lifecycleScope.launch {
                searchAdapter.submitData(pagingData)
            }
        })
    }

}
