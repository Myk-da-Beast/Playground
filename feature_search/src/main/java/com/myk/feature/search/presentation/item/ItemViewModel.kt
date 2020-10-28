package com.myk.feature.search.presentation.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.myk.feature.search.domain.usecase.GetItemsUseCase

class ItemViewModel(
    private val getItems: GetItemsUseCase
) : ViewModel() {
    fun getItemPages() = getItems().cachedIn(viewModelScope)
}
