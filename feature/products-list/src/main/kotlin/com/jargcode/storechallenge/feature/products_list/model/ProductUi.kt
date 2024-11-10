package com.jargcode.storechallenge.feature.products_list.model

import androidx.compose.runtime.Immutable
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.products_list.R

@Immutable
data class ProductUi(
    val code: String,
    val name: String,
    val price: String,
    val imageUrl: String?,
    val discountText: StringWrapper?,
) {

    companion object {

        val mock: ProductUi
            get() = ProductUi(
                code = "VOUCHER",
                name = "Cabify Voucher",
                price = "5.00€",
                imageUrl = "https://t4.ftcdn.net/jpg/02/35/86/91/360_F_235869128_VJOGMOTznY6PzXr2DXw0osnpvmCOlmm7.jpg",
                discountText = StringWrapper(text = "Buy 2, get 1 free")
            )

    }

}

// region Mappers

fun Product.toProductUi() = ProductUi(
    code = code,
    name = name,
    price = price.toFormattedPrice(),
    imageUrl = when { // For demo purposes
        code.equals("VOUCHER", ignoreCase = true) -> "https://t4.ftcdn.net/jpg/02/35/86/91/360_F_235869128_VJOGMOTznY6PzXr2DXw0osnpvmCOlmm7.jpg"
        code.equals("TSHIRT", ignoreCase = true) -> "https://m.media-amazon.com/images/I/61gn3DFLlWL._AC_UY1000_.jpg"
        code.equals("MUG", ignoreCase = true) -> "https://img.freepik.com/premium-photo/purple-coffee-mug-with-white-rim-plain-surface-generative-ai_900775-28399.jpg"
        else -> null
    },
    discountText = when (val productDiscount = discount) {
        is FixedPrice -> {
            StringWrapper(
                resId = R.string.discount_fixed_price_description,
                args = arrayOf(productDiscount.minQuantity, productDiscount.fixedPrice.toFormattedPrice())
            )
        }

        is FreeItem -> {
            StringWrapper(
                resId = R.string.discount_free_item_description,
                args = arrayOf(productDiscount.minQuantity)
            )
        }

        else -> null
    }
)

// endregion Mappers