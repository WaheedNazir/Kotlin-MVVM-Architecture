package com.kotlin.mvvm.ui.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.news.News
import com.kotlin.mvvm.utils.extensions.inflate
import kotlinx.android.synthetic.main.row_news_article.view.*

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * The News adapter to show the news in a list.
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    /**
     * List of news articles
     */
    private var newsArticles: List<News> = emptyList()


    var onNewsClicked: ((News) -> Unit)? = null

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.row_news_article))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(newsArticles[position])

    /**
     * Number of items in the list to display
     */
    override fun getItemCount() = newsArticles.size

    /**
     * View Holder Pattern
     */
    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: News) = with(itemView) {
            tvNewsItemTitle.text = newsArticle.title
            tvNewsAuthor.text = newsArticle.author
            //TODO: need to format date
            tvListItemDateTime.text = newsArticle.publishedAt

            Glide.with(context)
                .load(newsArticle.urlToImage)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(ivNewsImage)

            itemView.setOnClickListener {
                onNewsClicked?.invoke(newsArticle)
            }

        }
    }

    /**
     * Swap function to set new data on updating
     */
    fun replaceItems(items: List<News>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}