package com.jargcode.storechallenge.feature.cart.data

import com.jargcode.storechallenge.core.domain.cart.model.Cart.CartProduct
import com.jargcode.storechallenge.core.domain.discounts.model.Discount
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FixedPrice
import com.jargcode.storechallenge.core.domain.discounts.model.Discount.FreeItem
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.feature.cart.model.toCartItemUi

private fun cartProduct(
    discount: Discount? = null,
    quantity: Int = 1,
) = CartProduct(
    product = Product(
        code = "VOUCHER",
        name = "Cabify Voucher",
        price = 5.0,
        discount = discount
    ),
    quantity = quantity
)

fun cartProductUi(
    discount: Discount? = null,
    quantity: Int = 1,
) = cartProduct(
    discount = discount,
    quantity = quantity
).toCartItemUi()

fun cartProducts(
    voucherWithDiscount: Boolean = false,
    tShirtWithDiscount: Boolean = false,
): List<CartProduct> = listOf(
    CartProduct(
        product = Product(
            code = "VOUCHER",
            name = "Cabify Voucher",
            price = 5.0,
            discount = if (voucherWithDiscount) {
                FreeItem(
                    productCode = "VOUCHER",
                    minQuantity = 2,
                )
            } else {
                null
            }
        ),
        quantity = if (voucherWithDiscount) 2 else 1
    ),
    CartProduct(
        product = Product(
            code = "TSHIRT",
            name = "Cabify T-Shirt",
            price = 20.0,
            discount = if (tShirtWithDiscount) {
                FixedPrice(
                    productCode = "VOUCHER",
                    minQuantity = 3,
                    fixedPrice = 19.0
                )
            } else {
                null
            }
        ),
        quantity = if (tShirtWithDiscount) 3 else 1
    ),
    CartProduct(
        product = Product(
            code = "MUG",
            name = "Cabify Coffee Mug",
            price = 7.50,
            discount = null
        ),
        quantity = 1
    ),
)

fun cartProductsUi(
    voucherWithDiscount: Boolean = false,
    tShirtWithDiscount: Boolean = false,
) = cartProducts(
    voucherWithDiscount = voucherWithDiscount,
    tShirtWithDiscount = tShirtWithDiscount
).map { it.toCartItemUi() }