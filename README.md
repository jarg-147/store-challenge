# Cabify Mobile Challenge

This is my solution to the mobile challenge of Cabify.

You can check the challenge details here: [Cabify Mobile Challenge](https://github.com/cabify/MobileChallenge)

**Table of Contents**
- [Requirements](#requirements)
- [Setup](#setup)
- [Screens](#screens)
- [Architecture](#architecture)
- [Tests](#tests)
- [Libraries](#libraries)

## Requirements

UX:

- Be able to pick products from a list
- Understand what items are being purchased
- Checkout products to get the resulting price and the discounts applied

Code:

- Code should support discounts changing frequently
- Code must be written as production-ready code
- Written in Kotlin

## Setup
To run this project:

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on an emulator or a connected device.

> [!IMPORTANT]  
> Since the discounts are not obtained remotely, I have simulated a remote data source that returns the discounts mentioned in the challenge details.

These discounts can be changed by modifying the code in [DemoDiscountsRemoteDataSourceImpl](core/network/src/main/kotlin/com/jargcode/storechallenge/core/network/discounts/DemoDiscountsRemoteDataSourceImpl.kt), but the following considerations must be taken into account:
- Discount **MUST** have a valid `productId`.
- Discount **MUST** have a valid type `FREE_ITEM | FIXED_PRICE`.
- Discount `minQuantity` **MUST** be greater than 0.
- If discount type is `FIXED_PRICE`, `fixedPrice` **MUST** be greater than 0.
  
If any of these cases is not met, the discount will not be visible.

## Screens

Based on the UX requirements, I have decided to choose to create three screens:

- **Products list:** Shows a list of products in the Cabify store, highlighting those with discounts with a tag and a promotional text. *(Requirement #1)*

- **Cart:** Displays the products that the user has added to the cart, along with the quantity, the price of the products and the total amount of the purchase (without discounts applied yet). *(Requirement #2)*

- **Checkout:** Displays the summary of the purchase, showing the user the products, with their quantity, total price and discounts applied. *(Requirement #3)*

#### Screenshots

|                      Products list                      |                      Cart                      |                      Checkout                      |
|:-------------------------------------------------------:|:----------------------------------------------:|:--------------------------------------------------:|
| <img src="docs/images/products_list.png" width="200" /> | <img src="docs/images/cart.png" width="200" /> | <img src="docs/images/checkout.png" width="200" /> |

## Architecture

## Tests

I have included different types of Unit, UI and End to End (E2E) tests.

My main priority was to add tests to assert the desired result with the different examples given in the challenge detail.

Examples:

    Items: VOUCHER, TSHIRT, MUG
    Total: 32.50€

    Items: VOUCHER, TSHIRT, VOUCHER
    Total: 25.00€

    Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
    Total: 81.00€

    Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
    Total: 74.50€

Tests that check this examples can be found in [GetCheckoutSummaryUseCaseTest](core/testing/src/test/kotlin/com/jargcode/storechallenge/core/testing/checkout/useCase/GetCheckoutSummaryUseCaseTest.kt) (Unit test) and in [CreatePurchaseExamplesE2E](app/src/androidTest/java/com/jargcode/storechallenge/app/purchase/CreatePurchaseExamplesE2E.kt) (E2E test)


> [!NOTE]  
> For the E2E UI test, I have used Jake Wharton testing robot strategy.

I have also added Unit tests for each ViewModel and UI tests for each screen / component that I have considered important. These tests can be found in each feature module.

## Libraries
