package com.jargcode.storechallenge.feature.products_list

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jargcode.storechallenge.feature.products_list.data.productsUi
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
    fun givenUiStateIsLoading_thenDisplayLoadingIndicator() {
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Loading,
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val loadingContentDescription = context.getString(DesignSystemRes.string.loading_indicator_content_description)

        composeTestRule
            .onNodeWithContentDescription(loadingContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateIsError_thenDisplayErrorView() {
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
    fun givenUiStateIsSuccess_whenHasProducts_thenDisplaysProducts() {
        val products = productsUi()
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Success(
                    products = products
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val productsListContentTestTag = context.getString(R.string.product_list_content_test_tag)

        val lazyColumn = composeTestRule
            .onNodeWithTag(productsListContentTestTag, useUnmergedTree = true)

        products.forEach { productUi ->
            lazyColumn
                .performScrollToNode(hasText(productUi.name))
                .assertIsDisplayed()
        }
    }

    @Test
    fun givenUiStateIsSuccess_whenNoProducts_thenDisplaysEmptyView() {
        composeTestRule.setContent {
            ProductsListScreen(
                uiState = ProductsListUiState.Success(
                    products = emptyList()
                ),
                onUiEvent = {},
                snackbarState = SnackbarHostState()
            )
        }

        val emptyTitle = context.getString(R.string.empty_placeholder_title)
        val emptyText = context.getString(R.string.empty_placeholder_text)

        composeTestRule
            .onNodeWithText(emptyTitle, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(emptyText, useUnmergedTree = true)
            .assertIsDisplayed()

    }

}