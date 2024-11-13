package com.jargcode.storechallenge.core.testing.cart

import com.jargcode.storechallenge.core.domain.cart.model.SavedCartProduct

fun voucherCart(
    quantity: Int = 1,
) = listOf(
    SavedCartProduct(
        productCode = "VOUCHER",
        quantity = quantity
    )
)

fun tShirtCart(
    quantity: Int = 1,
) = listOf(
    SavedCartProduct(
        productCode = "TSHIRT",
        quantity = quantity
    )
)

fun mugCart(
    quantity: Int = 1,
) = listOf(
    SavedCartProduct(
        productCode = "MUG",
        quantity = quantity
    )
)

// Items: VOUCHER, TSHIRT, MUG
// Total: 32.50€
fun example1() = listOf(
    SavedCartProduct(
        productCode = "VOUCHER",
        quantity = 1
    ),
    SavedCartProduct(
        productCode = "TSHIRT",
        quantity = 1
    ),
    SavedCartProduct(
        productCode = "MUG",
        quantity = 1
    )
)

// Items: VOUCHER, TSHIRT, VOUCHER
// Total: 25.00€
fun example2() = listOf(
    SavedCartProduct(
        productCode = "VOUCHER",
        quantity = 2
    ),
    SavedCartProduct(
        productCode = "TSHIRT",
        quantity = 1
    )
)

// Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
// Total: 81.00€
fun example3() = listOf(
    SavedCartProduct(
        productCode = "TSHIRT",
        quantity = 4
    ),
    SavedCartProduct(
        productCode = "VOUCHER",
        quantity = 1
    )
)

// Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
// Total: 74.50€
fun example4() = listOf(
    SavedCartProduct(
        productCode = "VOUCHER",
        quantity = 3
    ),
    SavedCartProduct(
        productCode = "TSHIRT",
        quantity = 3
    ),
    SavedCartProduct(
        productCode = "MUG",
        quantity = 1
    )
)