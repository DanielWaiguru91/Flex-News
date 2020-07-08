package tech.danielwaiguru.flexnews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trending_news.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.NewsAdapter
import tech.danielwaiguru.flexnews.ui.MainActivity
import tech.danielwaiguru.flexnews.ui.NewsViewModel
import tech.danielwaiguru.flexnews.utility.Result

class TrendingNewsFragment : Fragment(){
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
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
        setData()
        viewModel.trendingNews.observe(viewLifecycleOwner, Observer { it ->
            when(it){
                is Result.Success ->{
                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.articleDifference.submitList(it.articles)
                    }
                }
                is Result.Loading ->{
                    showProgressBar()
                }
                is Result.Failure ->{
                    hideProgressBar()
                    Log.e("Trending News Fragment", "Error occurred ")
                }
            }
        })
    }
    private fun showProgressBar(){
        PaginationProgressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        PaginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setData(){
        newsAdapter = NewsAdapter()
        TrendingNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}