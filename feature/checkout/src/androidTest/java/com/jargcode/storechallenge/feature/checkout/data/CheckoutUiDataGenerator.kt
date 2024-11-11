package com.jargcode.storechallenge.feature.checkout.data

import com.jargcode.storechallenge.core.ui.utils.StringWrapper
import com.jargcode.storechallenge.core.ui.utils.extensions.toFormattedPrice
import com.jargcode.storechallenge.feature.checkout.R
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.AppliedDiscountUi
import com.jargcode.storechallenge.feature.checkout.model.CheckoutSummaryUi.CheckoutProductUi

fun voucherCheckoutUi(
    quantity: Int = 1,
    pricePerUnit: Double = 5.0
) = listOf(
    CheckoutProductUi(
        code = "VOUCHER",
        name = "Cabify Voucher",
        pricePerUnit = pricePerUnit.toFormattedPrice(),
        totalPrice = (pricePerUnit * quantity).toFormattedPrice(),
        quantity = quantity
    )
)

fun tShirtCheckoutUi(
    quantity: Int = 1,
    pricePerUnit: Double = 20.0
) = listOf(
    CheckoutProductUi(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        pricePerUnit = pricePerUnit.toFormattedPrice(),
        totalPrice = (pricePerUnit * quantity).toFormattedPrice(),
        quantity = quantity
    )
)

fun mugCheckoutUi(
    quantity: Int = 1,
    pricePerUnit: Double = 7.5
) = listOf(
    CheckoutProductUi(
        code = "MUG",
        name = "Cabify Coffe Mug",
        pricePerUnit = pricePerUnit.toFormattedPrice(),
        totalPrice = (pricePerUnit * quantity).toFormattedPrice(),
        quantity = quantity
    )
)

fun appliedFreeItemDiscount(
    quantity: Int = 1,
    productName: String,
    totalDiscounted: Double,
    timesApplied: Int
) = listOf(
    AppliedDiscountUi(
        discountInfo = StringWrapper(resId = R.string.buy_and_get_free_discount_info, args = arrayOf(quantity, productName)),
        totalDiscounted = totalDiscounted.toFormattedPrice(),
        timesApplied = timesApplied
    )
)

fun appliedFixedPriceDiscount(
    productName: String,
    totalDiscounted: Double,
    timesApplied: Int
) = listOf(
    AppliedDiscountUi(
        discountInfo = StringWrapper(resId = R.string.reduced_price_discount_info, args = arrayOf(productName)),
        totalDiscounted = totalDiscounted.toFormattedPrice(),
        timesApplied = timesApplied
    )
)