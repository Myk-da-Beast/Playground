package com.myk.feature.search.presentation.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.myk.feature.search.R
import com.myk.feature.search.databinding.ItemFragmentBinding
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.library.base.presentation.BaseAdapter
import com.myk.library.base.presentation.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

// this is the solution for dynamic feature dependency injection with koin
/**
 * This screen allows a user to search for Pokemon!
 */
class ItemFragment : Fragment(R.layout.item_fragment) {

    private val viewModel: ItemViewModel by viewModel()
    private val binding by viewBinding(ItemFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemsAdapter()
        binding.recycleView.adapter = adapter

        adapter.addLoadStateListener {
            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = it.source.append as? LoadState.Error
                ?: it.source.prepend as? LoadState.Error
                ?: it.append as? LoadState.Error
                ?: it.prepend as? LoadState.Error
            errorState?.let { error ->
                Toast.makeText(
                    requireContext(),
                    "Whooops ${error.error}",
                    Toast.LENGTH_LONG
                ).show()
                adapter.retry()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getItemPages().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<ItemDomainModel>() {
        override fun areItemsTheSame(
            oldItem: ItemDomainModel,
            newItem: ItemDomainModel
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: ItemDomainModel,
            newItem: ItemDomainModel
        ): Boolean = oldItem == newItem
    }

    class ItemsAdapter :
        PagingDataAdapter<ItemDomainModel, ItemViewHolder>(ItemDiffUtil()) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(
                PokemonItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                // onItemClickListener?.invoke(item)
            }
            holder.bind(getItem(position))
        }
    }

    class ItemViewHolder(
        binding: PokemonItemBinding
    ) : BaseAdapter.ViewHolder<ItemDomainModel, PokemonItemBinding>(binding) {
        override fun bind(item: ItemDomainModel?) {
            binding.imageView.load(item?.imageUrl)
        }
    }
}
