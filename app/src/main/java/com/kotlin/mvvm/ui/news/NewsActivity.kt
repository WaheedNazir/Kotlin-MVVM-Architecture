package com.kotlin.mvvm.ui.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.base.BaseActivity
import com.kotlin.mvvm.databinding.ActivityNewsArticlesBinding
import com.kotlin.mvvm.databinding.EmptyLayoutNewsArticleBinding
import com.kotlin.mvvm.databinding.ProgressLayoutNewsArticleBinding
import com.kotlin.mvvm.utils.extensions.load
import com.kotlin.mvvm.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Waheed on 04,November,2019
 */

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsArticlesBinding>() {

    companion object {
        const val KEY_COUNTRY_SHORT_KEY: String = "COUNTRY_SHORT_KEY"
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
     * Create Binding
     */
    override fun createBinding(): ActivityNewsArticlesBinding {
        return ActivityNewsArticlesBinding.inflate(layoutInflater)
    }

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.newsList.setEmptyView(binding.progressView.progressView)
        binding.newsList.setProgressView(binding.emptyView.emptyView)

        adapter = NewsAdapter()
        adapter.onNewsClicked = {
            //TODO Your news item click invoked here
        }

        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)

        getNewsOfCountry(intent?.getStringExtra(KEY_COUNTRY_SHORT_KEY) ?: "")
    }

    /**
     * Get country news using Network & DB Bound Resource
     * Observing for data change from DB and Network Both
     */
    private fun getNewsOfCountry(countryKey: String) {
        newsArticleViewModel.getNewsArticles(countryKey).observe(this) {
            when {
                it.status.isLoading() -> {
                    binding.newsList.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it?.load(binding.newsList) { news ->
                        adapter.replaceItems(news ?: emptyList())
                    }
                }
                it.status.isError() -> {
                    toast(it.errorMessage ?: "Something went wrong.")
                    finish()
                }
            }
        }
    }
}
