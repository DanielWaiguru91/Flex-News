package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article
import timber.log.Timber

class MainNewsAdapter: PagingDataAdapter<Article, MainViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {article ->
            Timber.d(article.url)
            holder.itemView.apply {
                Picasso.get().load(article.urlToImage)
                    .error(R.drawable.ic_broken_image)
                    .into(articleImage)
                articleTitle.text = article.title
                articleSource.text = article.source.name
                articlePublishedAt.text = article.publishedAt
                articleDescription.text = article.description
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_item, parent, false
            )
        )
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}