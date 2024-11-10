package com.jargcode.storechallenge.core.testing.products

import com.jargcode.storechallenge.core.domain.products.model.Product

fun getFakeProducts() = listOf(
    Product(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.0,
        discount = null
    ),
    Product(
        code = "TSHIRT",
        name = "Cabify T-Shirt",
        price = 20.0,
        discount = null
    ),
    Product(
        code = "MUG",
        name = "Cabify Coffee Mug",
        price = 7.50,
        discount = null
    ),
)