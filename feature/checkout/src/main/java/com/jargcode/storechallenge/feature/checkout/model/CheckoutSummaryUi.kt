package com.jargcode.storechallenge.feature.checkout.model

import androidx.compose.runtime.Immutable
import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.checkout.model.CheckoutSummary
import com.jargcode.storechallenge.core.domain.discounts.model.AppliedDiscount
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.R
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.AppliedDiscountUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.CheckoutProductUi

@Immutable
data class CheckoutSummaryUi(
    val products: List<CheckoutProductUi>,
    val subtotal: String,
    val appliedDiscounts: List<AppliedDiscountUi>,
    val total: String,
) {

    @Immutable
    data class CheckoutProductUi(
        val code: String,
        val name: String,
        val pricePerUnit: String,
        val totalPrice: String,
        val quantity: Int,
    )

    @Immutable
    data class AppliedDiscountUi(
        val discountInfo: StringWrapper,
        val totalDiscounted: String,
        val timesApplied: Int,
    )

    companion object {

        val mock: CheckoutSummaryUi
            get() = CheckoutSummaryUi(
                products = listOf(
                    CheckoutProductUi(
                        code = "VOUCHER",
                        name = "Cabify Voucher",
                        pricePerUnit = "5.00€",
                        quantity = 2,
                        totalPrice = "10.00€"
                    )
                ),
                subtotal = "10.00€",
                appliedDiscounts = listOf(
                    AppliedDiscountUi(
                        discountInfo = StringWrapper(
                            resId = R.string.buy_and_get_free_discount_info,
                            args = arrayOf(2, "Cabify Voucher")
                        ),
                        totalDiscounted = "5.00€",
                        timesApplied = 1
                    )
                ),
                total = "5.00€"
            )

    }

}

// region Mappers

fun CheckoutSummary.toCheckoutSummaryUi() = CheckoutSummaryUi(
    products = checkoutProducts.map { item -> item.toCheckoutItemUi() },
    subtotal = subtotal.toFormattedPrice(),
    appliedDiscounts = appliedDiscounts.map { appliedDiscount -> appliedDiscount.toAppliedDiscountUi() },
    total = total.toFormattedPrice()
)

fun CartProduct.toCheckoutItemUi() = CheckoutProductUi(
    code = product.code,
    name = product.name,
    pricePerUnit = product.price.toFormattedPrice(),
    quantity = quantity,
    totalPrice = total.toFormattedPrice()
)

fun AppliedDiscount.toAppliedDiscountUi() = AppliedDiscountUi(
    discountInfo = when (discount) {
        is FixedPrice -> {
            StringWrapper(
                resId = R.string.reduced_price_discount_info,
                args = arrayOf(product.name)
            )
        }

        is FreeItem -> {
            StringWrapper(
                resId = R.string.buy_and_get_free_discount_info,
                args = arrayOf(discount.minQuantity, product.name)
            )
        }
    },
    totalDiscounted = totalDiscounted.toFormattedPrice(),
    timesApplied = timesApplied
)

// endregion Mappers