package com.myk.feature.search.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.myk.feature.search.R
import com.myk.feature.search.databinding.PokemonFragmentBinding
import com.myk.feature.search.di.searchModule
import com.myk.library.base.presentation.viewBinding
import com.myk.library.data.di.dataModule
import com.myk.playground.di.sharedModules
import org.koin.core.context.loadKoinModules

// this is the solution for dynamic feature dependency injection with koin
private val loadModules by lazy {
    loadKoinModules(
        listOf(
            sharedModules,
            dataModule,
            searchModule
        )
    )
}

private fun injectFeatures() = loadModules

class PokemonFragment : Fragment(R.layout.pokemon_fragment) {
    private val binding by viewBinding(PokemonFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(index: Int) =
                if (index == 0) SearchFragment() else ItemFragment()
        }
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) "Pokemon" else "Items"
        }.attach()
    }
}
