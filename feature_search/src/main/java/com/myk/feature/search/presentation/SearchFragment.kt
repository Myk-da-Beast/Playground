package com.myk.feature.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myk.feature.search.databinding.PokemonItemBinding
import com.myk.feature.search.databinding.SearchFragmentBinding
import com.myk.feature.search.di.searchModule
import com.myk.feature.search.domain.model.Pokemon
import com.myk.playground.di.sharedModules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

// this is the solution for dynamic feature dependency injection with koin
private val loadModules by lazy {
    loadKoinModules(
        listOf(
            sharedModules,
            searchModule
        )
    )
}

private fun injectFeatures() = loadModules

/**
 * This screen allows a user to search for Pokemon!
 */
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()
    private val adapter = PokemonAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchFragmentBinding.inflate(inflater, container, false)

        binding.recycleView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(
            viewLifecycleOwner,
            {
                Log.w("Search", "$it")
                adapter.setItems(it)
            }
        )
    }

    class PokemonAdapter(
        private var items: List<Pokemon>
    ) : RecyclerView.Adapter<PokemonViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
            holder.bind(items.getOrNull(position))
        }

        override fun getItemCount() = items.size

        fun setItems(items: List<Pokemon>) {
            this.items = items
            this.notifyDataSetChanged()
        }
    }

    class PokemonViewHolder(
        private val binding: PokemonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon?) {
            binding.imageView.load(pokemon?.imageUrl)
        }
    }
}
