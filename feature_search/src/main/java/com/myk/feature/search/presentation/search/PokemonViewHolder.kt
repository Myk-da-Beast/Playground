package com.myk.feature.search.presentation.search

import coil.load
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.library.base.presentation.BaseAdapter
import java.util.Locale

class PokemonViewHolder(
    binding: PokemonItemBinding
) : BaseAdapter.ViewHolder<PokemonDomainModel, PokemonItemBinding>(binding) {
    override fun bind(item: PokemonDomainModel?) {
        binding.imageView.transitionName = item?.imageUrl
        binding.imageView.load(item?.imageUrl)
        binding.textView.text = item?.name?.capitalize(Locale.getDefault())
    }
}
