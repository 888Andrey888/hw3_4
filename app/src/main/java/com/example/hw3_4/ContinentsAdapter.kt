package com.example.hw3_4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw3_4.databinding.ViewContinentsBinding

class ContinentsAdapter(
    list: ArrayList<ContinentsModel>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ContinentsAdapter.ContinentsViewHolder>() {
    private var continentsList = list

    class ContinentsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ViewContinentsBinding.bind(item)
        fun bind(continents: ContinentsModel, onItemClickListener: OnItemClickListener) =
            with(binding) {
                tvTitle.text = continents.title
                tvDescription.text = continents.description
                Glide.with(imgContinents).load(continents.image).into(imgContinents)
                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(adapterPosition)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_continents, parent, false)
        return ContinentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContinentsViewHolder, position: Int) {
        holder.bind(continentsList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return continentsList.size
    }
}