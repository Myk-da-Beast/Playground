package com.myk.feature.search.presentation.pokemonDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.myk.feature.search.R
import com.myk.feature.search.databinding.PokemonDetailFragmentBinding
import com.myk.library.base.presentation.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonDetailFragment : Fragment(R.layout.pokemon_detail_fragment) {

    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: PokemonViewModel by viewModel {
        parametersOf(args.id)
    }
    private val binding by viewBinding(PokemonDetailFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.transitionName = args.transitionName

        viewModel.pokemon.observe(
            viewLifecycleOwner,
            {
                binding.imageView.load(it?.imageUrl)
            }
        )
    }
}
