package com.kotlin.mvvm.ui.countries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kotlin.mvvm.R
import com.kotlin.mvvm.di.base.Injectable
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.ui.news.NewsActivity

import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_country_list.view.*
import javax.inject.Inject

/**
 * Created by Waheed on 08,November,2019
 */

/**
 * A fragment representing a list of Items.
 */
class CountriesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var columnCount = 1
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var countriesViewModel: CountriesViewModel
    private var listOfCountries = ArrayList<Country>()
    private lateinit var thisView: View


    /**
     *
     */
    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            CountriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    /**
     *
     */
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this) // Providing the dependency
        super.onAttach(context)
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        thisView = inflater.inflate(R.layout.fragment_country_list, container, false)

        load()
        observeCountries()

        return thisView
    }

    /**
     *
     */
    private fun load() {
        countriesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)


        thisView.recyclerview_countries.layoutManager =
            if (columnCount <= 1) LinearLayoutManager(context) else GridLayoutManager(
                context,
                columnCount
            )
        countriesAdapter = CountriesAdapter(listOfCountries)
        thisView.recyclerview_countries.adapter = countriesAdapter

        countriesAdapter.onCountryClicked = { country ->
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra(NewsActivity.KEY_COUNTRY_SHORT_KEY, country.countryKey)
            startActivity(intent)
        }
    }

    /**
     *
     */
    private fun observeCountries() {
        countriesViewModel.getCountries().observe(viewLifecycleOwner, Observer {
            // You'l get list of countries here
            listOfCountries.clear()
            listOfCountries.addAll(it)
            countriesAdapter.notifyDataSetChanged()
        })
    }
}
