package com.jargcode.storechallenge.core.testing.discounts

import com.jargcode.storechallenge.core.domain.discounts.DiscountsRepository
import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import kotlinx.coroutines.flow.*

class FakeDiscountsRepository : DiscountsRepository {

    private val _discounts: MutableStateFlow<List<Discount>> = MutableStateFlow(emptyList())

    override fun getDiscounts(): Flow<List<Discount>> = _discounts

    fun setDiscounts(discounts: List<Discount>) {
        _discounts.update { discounts }
    }

}