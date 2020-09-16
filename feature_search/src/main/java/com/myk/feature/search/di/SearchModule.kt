package com.myk.feature.search.di

import com.myk.feature.search.data.repository.PokemonRepositoryImpl
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.feature.search.domain.usecase.GetPokemonUsecase
import com.myk.feature.search.domain.usecase.GetPokemonUsecaseImpl
import com.myk.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val searchModule: Module = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single<GetPokemonUsecase> { GetPokemonUsecaseImpl(get()) }
    viewModel { SearchViewModel(get()) }
}
