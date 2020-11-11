package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.load_state_item.view.*
import tech.danielwaiguru.flexnews.R

class ArticleLoadStateAdapter(private val retry: () -> Unit):
    LoadStateAdapter<ArticleLoadStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.load_state_item, parent, false
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
                footerLoad.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
                errorText.isVisible = loadState !is LoadState.Loading
            }
            if (loadState is LoadState.Error){
                itemView.errorText.text = loadState.error.localizedMessage
            }
        }
    }
}