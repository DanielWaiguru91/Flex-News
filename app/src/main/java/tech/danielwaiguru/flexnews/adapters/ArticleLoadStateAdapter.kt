package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import kotlinx.android.synthetic.main.load_state_footer.view.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.utils.visible

class ArticleLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<MainViewHolder>() {
    override fun onBindViewHolder(holder: MainViewHolder, loadState: LoadState) {
        holder.itemView.apply {
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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.load_state_footer, parent, false
        ))
    }
    interface RetryClickListener {
        fun onRetryButtonClick()
    }
}