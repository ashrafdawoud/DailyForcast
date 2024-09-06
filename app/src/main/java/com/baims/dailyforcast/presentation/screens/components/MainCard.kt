package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel

@Composable
fun MainCard(
    weatherPresentationModel: WeatherPresentationModel,
    paddingValue: PaddingValues,
    cites: Array<CityModel>,
    onCitySelect: (CityModel) -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    bottomStart = 100.dp,
                    bottomEnd = 100.dp
                )
            )
            .padding(top = paddingValue.calculateTopPadding())
            .height(500.dp)
            .fillMaxWidth()
    ) {
        DropdownList(
            cities = cites,
            selected = cites.first(),
            onSelection = onCitySelect
        )
        WeatherMainCard(weatherPresentationModel)
        Spacer(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .height(0.5.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
        WeatherDetailsCard(weatherPresentationModel)
    }
}
