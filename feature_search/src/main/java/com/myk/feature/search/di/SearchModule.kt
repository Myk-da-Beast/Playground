package com.myk.feature.search.di

import com.myk.feature.search.data.repository.SearchRepositoryImpl
import com.myk.feature.search.domain.SearchRepository
import com.myk.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val searchModule: Module = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    viewModel { SearchViewModel(get()) }
}
