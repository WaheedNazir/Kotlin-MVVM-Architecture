package com.kotlin.mvvm.ui.countryListing

import android.content.Intent
import android.os.Bundle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.ui.BaseActivity
import com.kotlin.mvvm.ui.newsArticles.NewsArticlesActivity
import com.kotlin.mvvm.ui.newsArticles.NewsArticlesActivity.Companion.KEY_COUNTRY_SHORT_KEY
import com.kotlin.mvvm.utils.ToastUtil

/**
 * Created by Waheed on 08,November,2019
 */

/**
 * Countries Listing Activity.
 */
class CountryListingActivity() : BaseActivity(),
    CountryListFragment.OnCountriesListClickListener {

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countires)

        // 1
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.fragment_container, CountryListFragment.newInstance(1))
                // 5
                .commit()
        }

    }

    override fun onCountriesListClick(country: Country) {
        val intent = Intent(this, NewsArticlesActivity::class.java)
        intent.putExtra(KEY_COUNTRY_SHORT_KEY, country.countryKey)
        startActivity(intent)
    }
}
