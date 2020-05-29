package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    /**
     * Diff Util Callback function
     */
    private val articleDifferCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Compare two article and return differences
     */
    val articleDifference = AsyncListDiffer(this, articleDifferCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return LayoutInflater.from(parent.context).inflate(
            R.layout.article_item.xml,
            parent,
            false
        )
    }

    override fun getItemCount(): Int {
        return  articleDifference.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleDifference.currentList[position]
        holder.itemView.apply {
            
        }
    }
}