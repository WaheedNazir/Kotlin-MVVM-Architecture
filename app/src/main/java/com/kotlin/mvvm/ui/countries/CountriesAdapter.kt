package com.kotlin.mvvm.ui.countries

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.RowCountryListBinding
import com.kotlin.mvvm.repository.model.countries.Country
import com.kotlin.mvvm.utils.load

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    var onCountryClicked: ((Country) -> Unit)? = null

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RowCountryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

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
    inner class ViewHolder(private val itemBinding: RowCountryListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onCountryClicked?.invoke(countries[adapterPosition])
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + itemBinding.tvCountryName.text + "'"
        }

        fun bindView(country: Country) {
            itemBinding.tvCountryName.text = country.displayName
            itemBinding.ivCountryImage.load(
                itemBinding.root.context,
                Uri.parse(country.countryFagUrl).toString()
            )
        }
    }
}
