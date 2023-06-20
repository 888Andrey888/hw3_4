package com.example.hw3_4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw3_4.databinding.ViewCountryBinding

class CountryAdapter(list: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private var countryList = list

    class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ViewCountryBinding.bind(item)
        fun bind(country: CountryModel) = with(binding){
            Glide.with(imgFlag).load(country.flag).into(imgFlag)
            tvTitle.text = country.title
            tvCapital.text = country.capital
            tvPopulation.text = country.population.toString()
            tvDescription.text = country.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}