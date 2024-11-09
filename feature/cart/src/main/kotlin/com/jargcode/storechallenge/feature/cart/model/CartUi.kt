package com.jargcode.storechallenge.feature.cart.model

import com.jargcode.storechallenge.core.domain.cart.model.Cart
import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartItemUi.DiscountInfo

data class CartUi(
    val items: List<CartItemUi>,
    val total: String,
) {

    data class CartItemUi(
        val code: String,
        val name: String,
        val price: String,
        val imageUrl: String?,
        val quantity: Int,
        val discountInfo: DiscountInfo?,
    ) {

        data class DiscountInfo(
            val minQuantityReached: Boolean,
            val text: StringWrapper,
        )

    }

    companion object {

        val mock: CartUi = CartUi(
            items = listOf(
                CartItemUi(
                    code = "VOUCHER",
                    name = "Cabify Voucher",
                    price = "5.00€",
                    imageUrl = "https://t4.ftcdn.net/jpg/02/35/86/91/360_F_235869128_VJOGMOTznY6PzXr2DXw0osnpvmCOlmm7.jpg",
                    quantity = 1,
                    discountInfo = DiscountInfo(
                        minQuantityReached = false,
                        text = StringWrapper(resId = R.string.discount_not_applied_free_item, args = arrayOf(1))
                    )
                )
            ),
            total = "5.00€"
        )

    }

}

fun Cart.toCartUi() = CartUi(
    items = items.map { it.toCartItemUi() },
    total = totalPriceWithoutDiscounts.toFormattedPrice(),
)

fun CartProduct.toCartItemUi(): CartItemUi {
    val productDiscount = product.discount

    val discountInfo = if (productDiscount != null) {
        val text = if (productDiscount.minQuantity > quantity) {
            val remainingQuantity = productDiscount.minQuantity - quantity
            when (productDiscount) {
                is FixedPrice -> {
                    StringWrapper(
                        resId = R.string.discount_not_applied_fixed_price,
                        args = arrayOf(remainingQuantity, productDiscount.fixedPrice.toFormattedPrice())
                    )
                }

                is FreeItem -> {
                    StringWrapper(
                        resId = R.string.discount_not_applied_free_item,
                        args = arrayOf(remainingQuantity)
                    )
                }
            }
        } else {
            StringWrapper(resId = R.string.discount_applied)
        }

        DiscountInfo(
            minQuantityReached = quantity >= productDiscount.minQuantity,
            text = text
        )
    } else {
        null
    }

    return CartItemUi(
        code = product.code,
        name = product.name,
        price = product.price.toFormattedPrice(),
        imageUrl = when { // For demo purposes
            product.code.equals("VOUCHER", ignoreCase = true) -> "https://t4.ftcdn.net/jpg/02/35/86/91/360_F_235869128_VJOGMOTznY6PzXr2DXw0osnpvmCOlmm7.jpg"
            product.code.equals("TSHIRT", ignoreCase = true) -> "https://m.media-amazon.com/images/I/61gn3DFLlWL._AC_UY1000_.jpg"
            product.code.equals("MUG", ignoreCase = true) -> "https://img.freepik.com/premium-photo/purple-coffee-mug-with-white-rim-plain-surface-generative-ai_900775-28399.jpg"
            else -> null
        },
        quantity = quantity,
        discountInfo = discountInfo
    )
}
