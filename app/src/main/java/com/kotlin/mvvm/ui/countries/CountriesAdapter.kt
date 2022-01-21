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
import kotlinx.android.synthetic.main.row_country_list.view.*

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    var onCountryClicked: ((Country) -> Unit)? = null

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country_list, parent, false)
    )

    /**
     *
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }

    /**
     *
     */
    override fun getItemCount(): Int = countries.size

    /**
     *
     */
    inner class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        private val imageViewCountry: ImageView = mView.iv_country_image
        private val textViewCountryName: TextView = mView.tv_country_name

        init {
            mView.setOnClickListener {
                onCountryClicked?.invoke(countries[adapterPosition])
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + textViewCountryName.text + "'"
        }

        fun bindView(country: Country) {
            textViewCountryName.text = country.displayName

            Glide.with(mView.context)
                .load(Uri.parse(country.countryFagUrl))
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(imageViewCountry)
        }
    }
}
