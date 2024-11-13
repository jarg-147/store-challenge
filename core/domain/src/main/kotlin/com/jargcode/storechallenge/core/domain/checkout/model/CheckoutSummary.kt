package com.jargcode.storechallenge.core.domain.checkout.model

import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.discounts.model.AppliedDiscount

data class CheckoutSummary(
    val checkoutProducts: List<CartProduct>,
    val subtotal: Double,
    val appliedDiscounts: List<AppliedDiscount>,
    val total: Double,
)