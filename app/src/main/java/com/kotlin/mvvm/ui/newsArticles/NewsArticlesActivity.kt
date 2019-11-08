package com.kotlin.mvvm.ui.newsArticles

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.ui.BaseActivity
import com.kotlin.mvvm.utils.ToastUtil
import com.kotlin.mvvm.utils.extensions.getViewModel
import com.kotlin.mvvm.utils.extensions.load
import com.kotlin.mvvm.utils.extensions.observe
import com.kotlin.mvvm.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_news_articles.*
import kotlinx.android.synthetic.main.empty_layout_news_article.*
import kotlinx.android.synthetic.main.progress_layout_news_article.*

/**
 * Created by Waheed on 04,November,2019
 */

/**
 * News Article Listing Activity.
 */
class NewsArticlesActivity : BaseActivity() {


    companion object {
        val KEY_COUNTRY_SHORT_KEY: String = "COUNTRY_SHORT_KEY"
    }

    private val newsArticleViewModel by lazy { getViewModel<NewsArticleViewModel>() }

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        news_list.setEmptyView(empty_view)
        news_list.setProgressView(progress_view)

        val adapter = NewsArticlesAdapter {
            toast(it.description.toString())
        }
        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)


        //intent.getStringExtra(KEY_COUNTRY_SHORT_KEY)
        /*
        * Observing for data change, Cater DB and Network Both
        * */
        newsArticleViewModel.getNewsArticles().observe(this) {
            when {
                it.status.isLoading() -> {
                    news_list.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it.load(news_list) {
                        // Update the UI as the data has changed
                        it?.let { adapter.replaceItems(it) }
                    }
                }
                it.status.isError() -> {
                    if (it.errorMessage != null)
                        ToastUtil.showCustomToast(this, it.errorMessage.toString())
                }
            }
        }

        /**
         * View model getting API response from server using Network Bound Resource Only
         */
        /*if (ConnectivityUtil.isConnected(this)) {
            newsArticleViewModel.getNewsArticlesFromServer().observe(this) {
                when {
                    it.status.isLoading() -> {

                    }
                    it.status.isSuccessful() -> {
                        it.load(news_list) {
                            it?.let {
                                adapter.replaceItems(it.articles)
                            }
                        }
                    }
                    it.status.isError() -> {

                    }
                }
            }
        } else {
            ToastUtil.showCustomToast(this, "No Internet Connection")
        }*/


        /**
         * View model getting API response from Server/DB using Executor*/
        /*newsArticleViewModel.getNewsArticlesFromServerUsingExecuter().observe(this, observer = {
            adapter.replaceItems(it)
        })*/
    }
}
