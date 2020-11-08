package com.myk.feature.search.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.domain.model.PokemonDomainModel

class PokemonAdapter :
    PagingDataAdapter<PokemonDomainModel, PokemonViewHolder>(SearchFragment.PokemonDiffUtil()) {

    private var onItemClickListener: ((PokemonDomainModel, ImageView) -> Unit)? = null

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item, holder.binding.imageView)
        }
        holder.bind(item)
    }

    fun setOnClickListener(listener: (PokemonDomainModel, ImageView) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(
        PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}
