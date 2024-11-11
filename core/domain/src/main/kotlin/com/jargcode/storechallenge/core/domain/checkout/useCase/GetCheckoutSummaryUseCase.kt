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
            val cartProducts = userCart.cartProducts
            val subTotal = userCart.totalPriceWithoutDiscounts
            val appliedDiscounts = calculateCartProductsDiscounts(cartProducts = cartProducts)
            val total = subTotal - appliedDiscounts.sumOf { discount -> discount.totalDiscounted }

            CheckoutSummary(
                checkoutProducts = cartProducts,
                subtotal = subTotal,
                appliedDiscounts = appliedDiscounts,
                total = total,
            )
        }

    private fun calculateCartProductsDiscounts(cartProducts: List<CartProduct>) = buildList {
        cartProducts.forEach { cartProduct ->
            cartProduct.product.discount?.let { productDiscount ->
                val minQuantity = productDiscount.minQuantity
                when (productDiscount) {
                    is FixedPrice -> {
                        if (cartProduct.quantity >= minQuantity) {
                            // Given a regular price of 20 and discounted price of 19,
                            // the discount per item is 1
                            val discountPerProduct = cartProduct.product.price - productDiscount.fixedPrice
                            // Multiply by quantity to get the total money to discount
                            val totalToDiscount = discountPerProduct * cartProduct.quantity

                            val timesApplied = cartProduct.quantity // Once per product

                            add(
                                AppliedDiscount(
                                    product = cartProduct.product,
                                    discount = productDiscount,
                                    totalDiscounted = totalToDiscount,
                                    timesApplied = timesApplied
                                )
                            )
                        }
                    }

                    is FreeItem -> {
                        // Reading the README of the challenge, I understand that if a user buys,
                        // for example, 4 products with a 2x1 discount, said user should receive 2 of them for free.
                        // Following code behaves based on this concept.
                        var discountableProductsQuantity = cartProduct.quantity
                        var timesApplied = 0

                        while (discountableProductsQuantity >= minQuantity) {
                            discountableProductsQuantity -= minQuantity
                            timesApplied++
                        }

                        if (timesApplied > 0) {
                            val totalToDiscount = cartProduct.product.price * timesApplied
                            add(
                                AppliedDiscount(
                                    product = cartProduct.product,
                                    discount = productDiscount,
                                    totalDiscounted = totalToDiscount,
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