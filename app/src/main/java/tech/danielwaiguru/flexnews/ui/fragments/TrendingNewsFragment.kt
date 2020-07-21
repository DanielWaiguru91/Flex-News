package tech.danielwaiguru.flexnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trending_news.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.NewsAdapter
import tech.danielwaiguru.flexnews.models.Result
import tech.danielwaiguru.flexnews.ui.main.MainActivity
import tech.danielwaiguru.flexnews.viewmodels.NewsViewModel

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
        viewModel.trendingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Result.Success ->{
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.articleDifference.submitList(it.articles)
                    }
                }
                is Result.Loading ->{
                    showProgressBar()
                    noInternetConnectionDialog()
                }
                is Result.Failure ->{
                    hideProgressBar()
                    Log.e("TrendingNewsFragment", "Error occurred ")
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
}