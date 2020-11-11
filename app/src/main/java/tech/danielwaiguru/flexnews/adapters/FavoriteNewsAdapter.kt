package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fav_news_item.view.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article

class FavoriteNewsAdapter(private val listener: ArticleClickListener):
    RecyclerView.Adapter<FavoriteNewsAdapter.FavoriteViewHolder>() {
    private val favArticles = ArrayList<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fav_news_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favArticle = favArticles[position]
        holder.itemView.apply {
            Picasso.get().load(favArticle.urlToImage).into(favArticleImage)
            favArticleTitle.text = favArticle.title
            favArticleDescription.text = favArticle.description
            favArticleSource.text = favArticle.author
            favArticlePublishedAt.text = favArticle.publishedAt
            setOnClickListener {
                listener.onArticleItemClicked(favArticle)
            }
        }
    }
    override fun getItemCount(): Int = favArticles.size
    internal fun setData(articles: List<Article>){
        favArticles.apply {
            clear()
            addAll(articles)
            notifyDataSetChanged()
        }
    }
    class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    interface ArticleClickListener {
        fun onArticleItemClicked(article: Article)
    }
}