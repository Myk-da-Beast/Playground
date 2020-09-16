package com.myk.feature.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myk.feature.search.databinding.SearchFragmentBinding
import com.myk.feature.search.di.searchModule
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SearchFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pokemon.observe(
            viewLifecycleOwner,
            {
                Log.w("Search", "$it")
            }
        )
    }
}
