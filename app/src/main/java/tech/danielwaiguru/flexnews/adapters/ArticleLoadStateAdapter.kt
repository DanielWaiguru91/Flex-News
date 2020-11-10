package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search_news.view.*
import kotlinx.android.synthetic.main.load_state_footer.view.*
import tech.danielwaiguru.flexnews.R

class ArticleLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<ArticleLoadStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        /*holder.itemView.apply {
            when (loadState){
                is LoadState.Loading -> {
                    footerLoad.visible()
                }
                is LoadState.Error -> {
                    errorText.visible()
                    retryButton.visible()
                }
            }
            retryButton.setOnClickListener {
                retry.invoke()
            }
        }*/
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.load_state_footer, parent, false
        ))
    }
    inner class LoadStateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.retryButton.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState){
            itemView.apply {
                paginationProgressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
                errorText.isVisible = loadState is LoadState.Error
            }
        }
    }
    interface RetryClickListener {
        fun onRetryButtonClick()
    }
}