package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.ArticleLoadStateAdapter
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
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        updateSearchQuery()
        searchJob?.cancel()
        searchViewModel.searchedNews.observe(viewLifecycleOwner, { pagingData ->
            searchAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })
    }
    private fun updateSearchQuery(){
        etSearch.text.trim().let {
            if (it.isNotEmpty()){
                searchViewModel.setQuery(it.toString())
            }
        }
    }
    private fun setupRecyclerView() = searchNewsRecyclerView.apply {
        adapter = searchAdapter.withLoadStateHeaderAndFooter(
            header = ArticleLoadStateAdapter { searchAdapter.retry() },
            footer = ArticleLoadStateAdapter { searchAdapter.retry()}
        )
    }
}
