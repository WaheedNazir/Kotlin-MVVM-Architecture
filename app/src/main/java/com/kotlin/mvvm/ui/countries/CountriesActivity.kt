package com.kotlin.mvvm.ui.countries

import android.os.Bundle
import com.kotlin.mvvm.R
import com.kotlin.mvvm.base.BaseActivity
import com.kotlin.mvvm.databinding.ActivityCountiresBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Waheed on 08,November,2019
 */

@AndroidEntryPoint
class CountriesActivity : BaseActivity<ActivityCountiresBinding>() {

    //Note: You can get SharedPreferences instance by injecting it
    /*@Inject
    lateinit var sharedPreferences: SharedPreferences*/

    /**
     * Create Binding
     */
    override fun createBinding(): ActivityCountiresBinding =
        ActivityCountiresBinding.inflate(layoutInflater)

    /**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
