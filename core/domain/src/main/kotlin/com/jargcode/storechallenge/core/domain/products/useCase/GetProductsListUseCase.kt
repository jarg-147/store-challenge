package com.jargcode.storechallenge.core.domain.products.useCase

import com.jargcode.storechallenge.core.domain.discounts.DiscountsRepository
import com.jargcode.storechallenge.core.domain.products.ProductsRepository
import com.jargcode.storechallenge.core.domain.products.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val discountsRepository: DiscountsRepository,
) {

    operator fun invoke(): Flow<List<Product>> = combine(
        productsRepository.getProducts(),
        discountsRepository.getDiscounts(),
    ) { products, discounts ->
        products.map { product ->
            product.copy(
                discount = discounts.find { discount ->
                    discount.productId.equals(product.code, ignoreCase = true)
                }
            )
        }
    }

}