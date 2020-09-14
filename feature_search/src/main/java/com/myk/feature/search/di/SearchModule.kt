package com.myk.feature.search.di

import com.myk.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val searchModule: Module = module {
    viewModel { SearchViewModel(get()) }
}
