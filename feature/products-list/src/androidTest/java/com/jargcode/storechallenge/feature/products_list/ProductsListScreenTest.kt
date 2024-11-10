package com.jargcode.storechallenge.feature.products_list

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jargcode.storechallenge.feature.products_list.data.getFakeProductsUi
import com.jargcode.storechallenge.feature.products_list.model.ProductsListUiState
import org.junit.*
import com.jargcode.storechallenge.core.designsystem.R as DesignSystemRes

class ProductsListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: ComponentActivity

    @Before
    fun setUp() {
        context = composeTestRule.activity
    }

    @Test
    fun loadingIndicatorIsDisplayedWhenLoadingState() {
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Loading,
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val loadingContentDescription = context.getString(DesignSystemRes.string.loading_indicator)

        composeTestRule
            .onNodeWithContentDescription(loadingContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun errorViewIsDisplayedWhenErrorState() {
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Error,
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val errorViewTitle = context.getString(R.string.error_placeholder_title)
        val errorViewText = context.getString(R.string.error_placeholder_text)
        val retryButtonText = context.getString(R.string.error_placeholder_button)

        composeTestRule
            .onNodeWithText(errorViewTitle, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(errorViewText, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(retryButtonText, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun successContentIsDisplayedWhenSuccessState() {
        val fakeProducts = getFakeProductsUi()
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Success(
                    products = fakeProducts
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val productsListContentTestTag = context.getString(R.string.product_list_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(productsListContentTestTag, useUnmergedTree = true)

        // First product
        fakeProducts.forEach { productUi ->
            lazyColumn
                .performScrollToNode(hasText(productUi.name))
                .assertIsDisplayed()
        }
    }

}