package com.jargcode.storechallenge.core.domain.products.useCase

import com.jargcode.storechallenge.core.domain.products.ProductsRepository
import com.jargcode.storechallenge.core.domain.products.model.Product
import com.jargcode.storechallenge.core.domain.promotions.PromotionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val promotionsRepository: PromotionsRepository,
) {

    operator fun invoke(): Flow<List<Product>> = combine(
        productsRepository.getProducts(),
        promotionsRepository.getPromotions(),
    ) { products, promotions ->
        products.map { product ->
            product.copy(
                promotion = promotions.find { promotion ->
                    promotion.productId.equals(product.code, ignoreCase = true)
                }
            )
        }
    }

}