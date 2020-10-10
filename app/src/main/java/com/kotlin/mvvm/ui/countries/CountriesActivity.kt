package com.kotlin.mvvm.ui.countries

import android.os.Bundle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.ui.BaseActivity

/**
 * Created by Waheed on 08,November,2019
 */
class CountriesActivity : BaseActivity() {

    /**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countires)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CountriesFragment.newInstance(1))
                .commit()
        }
    }
}
