package com.kotlin.mvvm.ui.countries


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kotlin.mvvm.R
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.ui.countries.CountryListFragment.OnCountriesListClickListener
import kotlinx.android.synthetic.main.row_country_list.view.*

/**
 * [RecyclerView.Adapter] that can display a [Country] and makes a call to the
 * specified [OnCountriesListClickListener].
 * TODO: Replace the implementation with code for your data type.
 */
class CountryListRecyclerViewAdapter(
    private val mValues: List<Country>,
    private val mListener: OnCountriesListClickListener?
) : RecyclerView.Adapter<CountryListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val country = v.tag as Country
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onCountriesListClick(country)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = mValues[position]
        holder.countryName.text = country.displayName

        Glide.with(holder.mView.context)
            .load(Uri.parse(country.countryFagUrl))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_banner_image)
                    .error(R.drawable.loading_banner_image)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(holder.countryImage)

        with(holder.mView) {
            tag = country
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val countryImage: ImageView = mView.iv_country_image
        val countryName: TextView = mView.tv_country_name

        override fun toString() = "${super.toString()} '${countryName.text}'"
    }
}
