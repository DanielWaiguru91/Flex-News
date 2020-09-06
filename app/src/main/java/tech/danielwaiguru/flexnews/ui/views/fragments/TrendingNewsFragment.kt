package tech.danielwaiguru.flexnews.ui.views.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_trending_news.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.NewsAdapter
import tech.danielwaiguru.flexnews.common.Constants.ARTICLE_URL
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.networking.NetworkStatusChecker
import tech.danielwaiguru.flexnews.utils.gone
import tech.danielwaiguru.flexnews.utils.toast
import tech.danielwaiguru.flexnews.utils.visible
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

@AndroidEntryPoint
class TrendingNewsFragment : Fragment(), NewsAdapter.ArticleClickListener{
    private val viewModel: NewsViewModel by viewModels()
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val newsAdapter by lazy {
        NewsAdapter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_trending_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAllNews()
        viewModel.trendingNews.observe(viewLifecycleOwner, Observer {
            onArticleListReceived(it)
        })
        viewModel.toast.observe(viewLifecycleOwner, Observer {
            if (it != null){
                requireActivity().toast(it)
            }
        })
    }

    private fun setUpRecyclerView(){
        TrendingNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
    private fun getAllNews(){
        PaginationProgressBar.visible()
        networkStatusChecker.performIfConnectedToInternet(::hasNoInternetConnection) {
            viewModel.fetchTrendingNews()
        }
    }
    private fun hasNoInternetConnection(){
        view?.let {
            requireActivity().toast("No connection")
            PaginationProgressBar.gone()
        }
    }
    private fun onArticleListReceived(articles: List<Article>){
        PaginationProgressBar.gone()
        checkArticlesList(articles)
        onArticleReceived(articles)
    }
    private fun checkArticlesList(articles: List<Article>){
        if (articles.isEmpty()) noData.visible() else noData.gone()
    }
    private fun onArticleReceived(articles: List<Article>){
        newsAdapter.setData(articles)
    }

    override fun onArticleClicked(article: Article) {
        val bundle = Bundle().apply {
            putParcelable(ARTICLE_URL, article)
        }
        view?.findNavController()
            ?.navigate(R.id.action_trendingNewsFragment_to_articleFragment, bundle)
    }
}