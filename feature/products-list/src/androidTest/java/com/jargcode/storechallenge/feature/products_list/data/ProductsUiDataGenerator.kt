package com.jargcode.storechallenge.feature.products_list.data

import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.core.testing.products.products
import com.jargcode.storechallenge.feature.products_list.model.toProductUi

fun productsUi() = products().map { product -> product.toProductUi() }

fun productUi(
    discount: Discount? = null
) = Product(
    code = "VOUCHER",
    name = "Cabify Voucher",
    price = 5.0,
    discount = discount
).toProductUi()