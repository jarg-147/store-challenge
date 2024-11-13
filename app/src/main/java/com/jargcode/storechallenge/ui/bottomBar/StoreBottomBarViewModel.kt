package com.jargcode.storechallenge.ui.bottomBar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jargcode.storechallenge.core.domain.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class StoreBottomBarViewModel @Inject constructor(
    private val cartRepository: CartRepository,
) : ViewModel() {

    val cartCount: StateFlow<Int> = cartRepository
        .getCartCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

}