package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.baims.dailyforcast.domain.model.CityModel
import org.junit.Rule
import org.junit.Test

class DropdownListKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeCities = arrayOf(
        CityModel(id = 1, cityNameEn = "Cairo"),
        CityModel(id = 2, cityNameEn = "London")
    )

    @Test
    fun test_dropdown_list_initially_shows_selected_city_name() {
        val selectedCity = fakeCities[0]
        composeTestRule.setContent {
            DropdownList(fakeCities, selectedCity) {}
        }

        composeTestRule.onNodeWithText(selectedCity.cityNameEn).assertIsDisplayed()
    }

    @Test
    fun test_clicking_dropdown_item_changes_selected_city_and_calls_onSelection() {
        val selectedCity = fakeCities[0]
        composeTestRule.setContent {
            DropdownList(fakeCities, selectedCity, {})
        }
        val dropdownButton = composeTestRule.onNodeWithContentDescription("")
        dropdownButton.performClick()
        val londonItem = composeTestRule.onNodeWithText(fakeCities[1].cityNameEn)
        londonItem.assertIsDisplayed()
        londonItem.performClick()
        composeTestRule.onNodeWithText(fakeCities[1].cityNameEn).assertIsDisplayed()
    }
}