package com.jargcode.storechallenge.feature.cart.model

import androidx.compose.runtime.Immutable
import com.jargcode.storechallenge.core.domain.cart.model.Cart
import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.cart.R
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi
import com.jargcode.storechallenge.feature.cart.model.CartUi.CartProductUi.DiscountInfo

@Immutable
data class CartUi(
    val cartProducts: List<CartProductUi>,
    val totalPriceWithoutDiscounts: String,
) {

    @Immutable
    data class CartProductUi(
        val code: String,
        val name: String,
        val price: String,
        val imageUrl: String?,
        val quantity: Int,
        val discountInfo: DiscountInfo?,
    ) {

        @Immutable
        data class DiscountInfo(
            val isApplicable: Boolean,
            val text: StringWrapper,
        )

    }

    companion object {

        val mock: CartUi = CartUi(
            cartProducts = listOf(
                CartProductUi(
                    code = "VOUCHER",
                    name = "Cabify Voucher",
                    price = "5.00€",
                    imageUrl = "https://t4.ftcdn.net/jpg/02/35/86/91/360_F_235869128_VJOGMOTznY6PzXr2DXw0osnpvmCOlmm7.jpg",
                    quantity = 1,
                    discountInfo = DiscountInfo(
                        isApplicable = false,
                        text = StringWrapper(resId = R.string.discount_free_item_not_applied, args = arrayOf(1))
                    )
                )
            ),
            totalPriceWithoutDiscounts = "5.00€"
        )

    }

}

// region Mappers

fun Cart.toCartUi() = CartUi(
    cartProducts = cartProducts.map { cartProduct -> cartProduct.toCartItemUi() },
    totalPriceWithoutDiscounts = totalPriceWithoutDiscounts.toFormattedPrice(),
)

fun CartProduct.toCartItemUi(): CartProductUi {
    val discountInfo = product.discount?.let { discount ->
        getDiscountInfo(
            discount = discount,
            productQuantity = quantity
        )
    }

    return CartProductUi(
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

fun getDiscountInfo(
    discount: Discount,
    productQuantity: Int,
): DiscountInfo {
    val text = if (discount.minQuantity > productQuantity) {
        val remainingQuantity = discount.minQuantity - productQuantity
        when (discount) {
            is FixedPrice -> {
                StringWrapper(
                    resId = R.string.discount_fixed_price_not_applied,
                    args = arrayOf(remainingQuantity, discount.fixedPrice.toFormattedPrice())
                )
            }

            is FreeItem -> {
                StringWrapper(
                    resId = R.string.discount_free_item_not_applied,
                    args = arrayOf(remainingQuantity)
                )
            }
        }
    } else {
        StringWrapper(resId = R.string.discount_applied)
    }

    return DiscountInfo(
        isApplicable = productQuantity >= discount.minQuantity,
        text = text
    )
}

// endregion Mappers