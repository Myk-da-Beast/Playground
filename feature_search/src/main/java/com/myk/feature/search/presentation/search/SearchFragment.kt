package com.myk.feature.search.presentation.search

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import com.myk.feature.search.R
import com.myk.feature.search.databinding.SearchFragmentBinding
import com.myk.feature.search.domain.model.PokemonDomainModel
import com.myk.feature.search.presentation.PokemonFragmentDirections
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
    private var recyclerViewState: Parcelable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonAdapter()

        adapter.setOnClickListener { pokemon, imageView ->
            recyclerViewState = binding.recycleView.layoutManager?.onSaveInstanceState()
            val extras = FragmentNavigatorExtras(
                imageView to pokemon.imageUrl
            )
            findNavController().navigate(
                PokemonFragmentDirections.actionPokemonFragmentToPokemonDetailFragment(
                    pokemon.id, pokemon.imageUrl
                ),
                extras
            )
        }
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

    override fun onResume() {
        super.onResume()
        Handler().post {
            binding.recycleView.layoutManager?.onRestoreInstanceState(recyclerViewState)
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
}
