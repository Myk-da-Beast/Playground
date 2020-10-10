package com.myk.feature.search.presentation

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
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.databinding.SearchFragmentBinding
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.library.base.presentation.BaseAdapter
import com.myk.library.base.presentation.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * This screen allows a user to search for Pokemon!
 */
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModel()
    private val binding by viewBinding(SearchFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonAdapter()
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
            viewModel.getPokemonPages().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    class PokemonDiffUtil : DiffUtil.ItemCallback<PokemonDomainModel>() {
        override fun areItemsTheSame(
            oldItem: PokemonDomainModel,
            newItem: PokemonDomainModel
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: PokemonDomainModel,
            newItem: PokemonDomainModel
        ): Boolean = oldItem == newItem
    }

    class PokemonAdapter :
        PagingDataAdapter<PokemonDomainModel, PokemonViewHolder>(PokemonDiffUtil()) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                // onItemClickListener?.invoke(item)
            }
            holder.bind(getItem(position))
        }
    }

    class PokemonViewHolder(
        binding: PokemonItemBinding
    ) : BaseAdapter.ViewHolder<PokemonDomainModel, PokemonItemBinding>(binding) {
        override fun bind(item: PokemonDomainModel?) {
            binding.imageView.load(item?.imageUrl)
        }
    }
}
