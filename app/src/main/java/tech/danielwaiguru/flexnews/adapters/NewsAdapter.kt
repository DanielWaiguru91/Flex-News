package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article

class NewsAdapter(private val listener: ArticleClickListener) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    /*inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    *//**
     * Diff Util Callback function
     *//*
    private val articleDifferCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    *//**
     * Compare two article and return differences
     *//*
    val articleDifference = AsyncListDiffer(this, articleDifferCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  articleDifference.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleDifference.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(articleImage)
            articleSource.text = article.source.name
            articleDescription.text = article.description
            articlePublishedAt.text = article.publishedAt
            articleTitle.text = article.title
            holder.itemView.setOnClickListener {
                listener.onArticleClicked(article)
            }
        }
    }*/
    private var articles = emptyList<Article>()
    interface ArticleClickListener{
        fun onArticleClicked(article: Article)
    }

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val articleTitle: TextView = itemView.findViewById(R.id.articleTitle)
        val articleDescription: TextView = itemView.findViewById(R.id.articleDescription)
        val articlePublishedAt: TextView = itemView.findViewById(R.id.articlePublishedAt)
        val articleSource: TextView = itemView.findViewById(R.id.articleSource)
        val articleImage: ImageView = itemView.findViewById(R.id.articleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.article_item,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.articleTitle.text = article.title
        holder.articleSource.text = article.source.name
        holder.articlePublishedAt.text = article.publishedAt
        holder.articleDescription.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.articleImage)
    }
    fun setData(articles: List<Article>){
        this.articles = articles
        notifyDataSetChanged()
    }
}