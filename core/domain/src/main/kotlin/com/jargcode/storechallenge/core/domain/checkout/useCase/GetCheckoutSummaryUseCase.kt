package com.jargcode.storechallenge.core.domain.checkout.useCase

import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.cart.useCase.GetUserCartUseCase
import com.jargcode.storechallenge.core.domain.checkout.model.CheckoutSummary
import com.jargcode.storechallenge.core.domain.discounts.model.AppliedDiscount
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCheckoutSummaryUseCase @Inject constructor(
    private val getUserCartUseCase: GetUserCartUseCase,
) {

    operator fun invoke(): Flow<CheckoutSummary> = getUserCartUseCase()
        .map { userCart ->
            val cartItems = userCart.items
            val subTotal = userCart.totalPriceWithoutDiscounts
            val appliedDiscounts = calculateCartItemsDiscounts(cartItems = cartItems)
            val total = subTotal - appliedDiscounts.sumOf { discount -> discount.totalDiscount }

            CheckoutSummary(
                checkoutItems = cartItems,
                subtotal = subTotal,
                appliedDiscounts = appliedDiscounts,
                total = total,
            )
        }

    private fun calculateCartItemsDiscounts(cartItems: List<CartProduct>) = buildList {
        cartItems.forEach { cartProduct ->
            cartProduct.product.discount?.let { productDiscount ->
                val minQuantity = productDiscount.minQuantity
                when (productDiscount) {
                    is FixedPrice -> {
                        if (cartProduct.quantity >= minQuantity) {
                            // Given a regular price of 20 and discounted price of 19,
                            // the discount per item is 1
                            val discountPerItem = cartProduct.product.price - productDiscount.fixedPrice
                            // Multiply by quantity to get the total money to discount
                            val totalToDiscount = discountPerItem * cartProduct.quantity

                            val timesApplied = cartProduct.quantity // Once per item

                            add(
                                AppliedDiscount(
                                    product = cartProduct.product,
                                    discount = productDiscount,
                                    totalDiscount = totalToDiscount,
                                    timesApplied = timesApplied
                                )
                            )
                        }
                    }

                    is FreeItem -> {
                        // Reading the README of the challenge, I understand that if a user buys,
                        // for example, 4 products with a 2x1 discount, said user should receive 2 of them for free.
                        // Following code behaves based on this concept.

                        var discountableItemsQuantity = cartProduct.quantity
                        var timesApplied = 0

                        while (discountableItemsQuantity >= minQuantity) {
                            discountableItemsQuantity -= minQuantity
                            timesApplied++
                        }

                        if (timesApplied > 0) {
                            val totalToDiscount = cartProduct.product.price * timesApplied
                            add(
                                AppliedDiscount(
                                    product = cartProduct.product,
                                    discount = productDiscount,
                                    totalDiscount = totalToDiscount,
                                    timesApplied = timesApplied
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}