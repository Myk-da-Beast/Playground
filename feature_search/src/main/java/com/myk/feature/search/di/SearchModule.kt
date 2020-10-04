package com.myk.feature.search.di

import com.myk.feature.search.data.repository.PokemonRepositoryImpl
import com.myk.feature.search.data.service.PokeApiService
import com.myk.feature.search.domain.repository.PokemonRepository
import com.myk.feature.search.domain.usecase.GetItemsUseCase
import com.myk.feature.search.domain.usecase.GetPokemonUseCase
import com.myk.feature.search.presentation.ItemViewModel
import com.myk.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule: Module = module {
    factory { get<Retrofit>().create(PokeApiService::class.java) }
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get(), get()) }
    single { GetPokemonUseCase(get()) }
    single { GetItemsUseCase(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ItemViewModel(get()) }
}
