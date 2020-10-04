package com.myk.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.myk.feature.search.R
import com.myk.feature.search.databinding.ItemFragmentBinding
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.library.base.presentation.BaseAdapter
import com.myk.library.base.presentation.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

// this is the solution for dynamic feature dependency injection with koin
/**
 * This screen allows a user to search for Pokemon!
 */
class ItemFragment : Fragment(R.layout.item_fragment) {

    private val viewModel: ItemViewModel by viewModel()
    private val binding by viewBinding(ItemFragmentBinding::bind)
    private val adapter = PokemonAdapter(listOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = adapter

        viewModel.items.observe(
            viewLifecycleOwner,
            {
                adapter.setItems(it)
            }
        )
    }

    class PokemonAdapter(
        items: List<ItemDomainModel>
    ) : BaseAdapter<ItemDomainModel, PokemonItemBinding, PokemonViewHolder>(items) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class PokemonViewHolder(
        binding: PokemonItemBinding
    ) : BaseAdapter.ViewHolder<ItemDomainModel, PokemonItemBinding>(binding) {
        override fun bind(item: ItemDomainModel?) {
            binding.imageView.load(item?.imageUrl)
        }
    }
}
