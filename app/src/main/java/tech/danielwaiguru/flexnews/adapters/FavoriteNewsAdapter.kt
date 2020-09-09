package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fav_news_item.view.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.utils.ArticleClickListener

class FavoriteNewsAdapter(private val listener: ArticleClickListener):
    RecyclerView.Adapter<MainViewHolder>() {
    private val favArticles = ArrayList<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fav_news_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val favArticle = favArticles[position]
        holder.itemView.apply {
            Picasso.get().load(favArticle.urlToImage).into(favArticleImage)
            favArticleTitle.text = favArticle.title
            favArticleDescription.text = favArticle.description
            favArticleSource.text = favArticle.author
            favArticlePublishedAt.text = favArticle.publishedAt
            listener.onArticleItemClicked(favArticle)
        }
    }

    override fun getItemCount(): Int = favArticles.size
    internal fun setData(articles: List<Article>){
        favArticles.apply {
            clear()
            addAll(articles)
        }
    }
}