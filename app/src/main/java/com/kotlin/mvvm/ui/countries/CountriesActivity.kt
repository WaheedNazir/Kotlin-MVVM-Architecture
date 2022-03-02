package com.kotlin.mvvm.ui.countries

import android.content.SharedPreferences
import android.os.Bundle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.ui.BaseActivity
import com.kotlin.mvvm.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Waheed on 08,November,2019
 */

@AndroidEntryPoint
class CountriesActivity : BaseActivity() {

    //Note: You can get SharedPreferences instance by injecting it
    /*@Inject
    lateinit var sharedPreferences: SharedPreferences*/

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

        // You can save your data like this
        // sharedPreferences.edit().putString("YOUR_KEY","123456").apply()

        // You can retrieve your data like this
        // toast(sharedPreferences.getString("YOUR_KEY","")!!)
    }
}
