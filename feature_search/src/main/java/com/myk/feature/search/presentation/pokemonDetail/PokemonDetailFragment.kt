package com.myk.feature.search.presentation.pokemonDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.myk.feature.search.R
import com.myk.feature.search.databinding.PokemonDetailFragmentBinding
import com.myk.feature.search.extension.setTextOrHide
import com.myk.library.base.presentation.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.Locale

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

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.imageView.transitionName = args.transitionName

        viewModel.pokemon.observe(viewLifecycleOwner) {
            binding.toolbar.title = it?.name?.capitalize(Locale.getDefault())
            binding.imageView.load(it?.imageUrl)
            if (it?.types?.isNotEmpty() == true) {
                val type1 = it.types.getOrNull(0)
                val type2 = it.types.getOrNull(1)
                binding.firstTypeChip.setTextOrHide(type1)
                binding.secondTypeChip.setTextOrHide(type2)
                binding.firstTypeChip.setChipBackgroundColorResource(it.getColorForType(type1))
                binding.secondTypeChip.setChipBackgroundColorResource(it.getColorForType(type2))
            }
        }
    }
}
