package com.jargcode.storechallenge.core.domain.cart.useCase

import com.jargcode.storechallenge.core.domain.cart.CartRepository
import com.jargcode.storechallenge.core.domain.cart.model.Cart
import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.products.useCase.GetProductsListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserCartUseCase @Inject constructor(
    private val getProductsListUseCase: GetProductsListUseCase,
    private val cartRepository: CartRepository,
) {

    operator fun invoke(): Flow<Cart> = combine(
        getProductsListUseCase(),
        cartRepository.getUserCartItems(),
    ) { products, cartItems ->

        val items = cartItems
            .mapNotNull { items ->
                // Filter unknown saved products
                val matchingProduct = products.find { product ->
                    product.code.equals(items.productCode, ignoreCase = true)
                }

                matchingProduct?.let { product ->
                    CartProduct(
                        product = product,
                        quantity = items.quantity,
                    )
                }
            }

        val total = items.sumOf { cartItem -> cartItem.total }

        Cart(
            items = items,
            totalPriceWithoutDiscounts = total,
        )
    }

}