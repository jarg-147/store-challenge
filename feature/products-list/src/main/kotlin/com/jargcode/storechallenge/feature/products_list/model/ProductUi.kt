package com.jargcode.storechallenge.feature.products_list.model

import androidx.compose.runtime.Immutable
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.core.domain.promotions.model.Promotion.FixedPrice
import com.jargcode.storechallenge.core.domain.promotions.model.Promotion.FreeItem
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.products_list.R

@Immutable
data class ProductUi(
    val id: String,
    val name: String,
    val price: String,
    val imageUrl: String?,
    val promotionText: StringWrapper?,
) {

    companion object {

        val mock: ProductUi = ProductUi(
            id = "VOUCHER",
            name = "Cabify Voucher",
            price = "5.00€",
            imageUrl = "https://sites.create-cdn.net/siteimages/22/1/6/221602/58/1/0/5810517/400x289.jpg",
            promotionText = StringWrapper(text = "Buy 2, get 1 free")
        )

    }

}

fun Product.toProductUi() = ProductUi(
    id = code,
    name = name,
    price = price.toFormattedPrice(),
    imageUrl = when { // For demo purposes
        code.equals("VOUCHER", ignoreCase = true) -> "https://sites.create-cdn.net/siteimages/22/1/6/221602/58/1/0/5810517/400x289.jpg"
        code.equals("TSHIRT", ignoreCase = true) -> "https://m.media-amazon.com/images/I/61gn3DFLlWL._AC_UY1000_.jpg"
        code.equals("MUG", ignoreCase = true) -> "https://img.freepik.com/premium-photo/purple-coffee-mug-with-white-rim-plain-surface-generative-ai_900775-28399.jpg"
        else -> null
    },
    promotionText = run {
        when (val promotion = promotion) {
            is FixedPrice -> StringWrapper(resId = R.string.when_buying_or_more_each, args = arrayOf(promotion.minQuantity, promotion.fixedPrice.toFormattedPrice()))
            is FreeItem -> StringWrapper(resId = R.string.buy_and_get_free, args = arrayOf(promotion.minQuantity))
            else -> null
        }
    }
)
