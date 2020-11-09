package tech.danielwaiguru.flexnews.ui.views.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_trending_news.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.ArticleLoadStateAdapter
import tech.danielwaiguru.flexnews.adapters.MainNewsAdapter
import tech.danielwaiguru.flexnews.networking.NetworkStatusChecker
import tech.danielwaiguru.flexnews.ui.viewmodels.NewsViewModel
import tech.danielwaiguru.flexnews.utils.toast

@AndroidEntryPoint
class TrendingNewsFragment : Fragment(){
    private val newsViewModel: NewsViewModel by viewModels()
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val newsAdapter by lazy {
        MainNewsAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAllNews()
        newsViewModel.toast.observe(viewLifecycleOwner){
            if (it != null){
                requireActivity().toast(it)
            }
        }
    }

    private fun setUpRecyclerView(){
        TrendingNewsRecyclerView.apply {
            adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = ArticleLoadStateAdapter { newsAdapter.retry() },
                footer = ArticleLoadStateAdapter { newsAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(activity)
        }

    }
    private fun getAllNews(){
        networkStatusChecker.performIfConnectedToInternet(::hasNoInternetConnection) {
            lifecycleScope.launch {
                newsViewModel.fetchNews().collectLatest {
                    newsAdapter.submitData(it)
                }
            }
        }
    }
    private fun hasNoInternetConnection(){
        view?.let {
            requireActivity().toast("No connection")
        }
    }
}