package com.myk.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.myk.feature.search.domain.model.ItemDomainModel
import com.myk.feature.search.domain.usecase.GetItemsUseCase

class ItemViewModel(
    getItems: GetItemsUseCase
) : ViewModel() {
    val items: LiveData<List<ItemDomainModel>> = getItems().asLiveData()
}
