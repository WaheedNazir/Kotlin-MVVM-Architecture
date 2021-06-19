package com.kotlin.mvvm.ui.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.ui.BaseActivity
import com.kotlin.mvvm.utils.ToastUtil
import com.kotlin.mvvm.utils.extensions.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news_articles.*
import kotlinx.android.synthetic.main.empty_layout_news_article.*
import kotlinx.android.synthetic.main.progress_layout_news_article.*

/**
 * Created by Waheed on 04,November,2019
 */

@AndroidEntryPoint
class NewsActivity : BaseActivity() {


    companion object {
        val KEY_COUNTRY_SHORT_KEY: String = "COUNTRY_SHORT_KEY"
    }

    private lateinit var adapter: NewsAdapter

    /**
     * RegistrationViewModel is used to set the username and password information (attached to
     * Activity's lifecycle and shared between different fragments)
     * EnterDetailsViewModel is used to validate the user input (attached to this
     * Fragment's lifecycle)
     *
     * They could get combined but for the sake of the codelab, we're separating them so we have
     * different ViewModels with different lifecycles.
     *
     * @Inject annotated fields will be provided by Dagger
     */
    private val newsArticleViewModel: NewsViewModel by viewModels()

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        news_list.setEmptyView(empty_view)
        news_list.setProgressView(progress_view)

        adapter = NewsAdapter()
        adapter.onNewsClicked = {
            //TODO Your news item click invoked here
        }

        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)

        getNewsOfCountry(intent?.getStringExtra(KEY_COUNTRY_SHORT_KEY)!!)
    }

    /**
     * Get country news using Network & DB Bound Resource
     * Observing for data change from DB and Network Both
     */
    private fun getNewsOfCountry(countryKey: String) {
        newsArticleViewModel.getNewsArticles(countryKey).observe(this, Observer {
            when {
                it.status.isLoading() -> {
                    news_list.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it?.load(news_list) { news ->
                        adapter.replaceItems(news!!)
                    }
                }
                it.status.isError() -> {
                    if (it.errorMessage != null)
                        ToastUtil.showCustomToast(this, it.errorMessage.toString())
                }
            }
        })
    }
}
