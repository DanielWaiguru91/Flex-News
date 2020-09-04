package tech.danielwaiguru.flexnews.ui.fragments

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trending_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.danielwaiguru.flexnews.App
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.NewsAdapter
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.models.Success
import tech.danielwaiguru.flexnews.networking.NetworkStatusChecker
import tech.danielwaiguru.flexnews.ui.main.MainActivity
import tech.danielwaiguru.flexnews.utils.gone
import tech.danielwaiguru.flexnews.utils.toast
import tech.danielwaiguru.flexnews.utils.visible
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

class TrendingNewsFragment : Fragment(), NewsAdapter.ArticleClickListener{
    private lateinit var viewModel: NewsViewModel
    //private lateinit var newsAdapter: NewsAdapter
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val remoteNewsApi  by lazy { App.remoteNewsApi }
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
        viewModel = (activity as MainActivity).newsViewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        TrendingNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        getAllNews()
    }
    private fun noInternetConnectionDialog(){
        val dialogTitle = getString(R.string.dialogTitle)
        val dialogMessage = getString(R.string.dialogMessage)
        val positiveButton = getString(R.string.retryButton)
        val negativeButton = getString(R.string.cancelButton)
        activity?.let {
            val networkDialog = AlertDialog.Builder(it)
            networkDialog.setTitle(dialogTitle)
            networkDialog.setMessage(dialogMessage)
            networkDialog.setIcon(R.drawable.ic_warning)
            networkDialog.setPositiveButton(positiveButton) { _, _ ->  }
            networkDialog.setNegativeButton(negativeButton) { dialog, _ ->
                dialog.dismiss()
                activity?.finish()
            }
            networkDialog.create().show()
        }

    }
    private fun getAllNews(){
        PaginationProgressBar.visible()
        networkStatusChecker.performIfConnectedToInternet {
            lifecycleScope.launch(Dispatchers.Main) {
                val result = remoteNewsApi.getTrendingNews()
                if (result is Success){
                    Log.d("API", "${result.data}")
                    onArticleListReceived(result.data)
                }
                else{
                    onGetArticlesFailed()
                }
            }
        }
    }
    private fun onGetArticlesFailed(){
        PaginationProgressBar.gone()
        activity?.toast("News fetch failed")
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
        activity?.toast("Article ${article.title} clicked")
    }
}