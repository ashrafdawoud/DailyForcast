package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.baims.dailyforcast.R
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

@Composable
private fun DropdownList(
    cities: Array<CityModel>,
    selected: CityModel,
    onSelection: (CityModel) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedCity = remember { mutableStateOf(selected) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,

        ) {
        Button(onClick = { expanded.value = !expanded.value }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = ""
                )
                Text(
                    text = selectedCity.value.cityNameEn,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                    modifier = Modifier.padding(start = 5.dp, end = 8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_drop_down_arrow),
                    contentDescription = ""
                )
            }
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { }
        ) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = city.cityNameEn,
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
                        )
                    },
                    onClick = {
                        expanded.value = false
                        selectedCity.value = city
                        onSelection.invoke(city)
                    })
            }
        }
    }
}

@Composable
private fun WeatherMainCard(weatherPresentationModel: WeatherPresentationModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = weatherPresentationModel.iconUrl,
                contentDescription = "Sun",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weatherPresentationModel.dayName,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = weatherPresentationModel.temperatureDegrees,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 50.sp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = weatherPresentationModel.temperatureStatus.status,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun WeatherDetailsCard(weatherPresentationModel: WeatherPresentationModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherDetailItem(
            icon = painterResource(id = R.drawable.wet_pow),
            label = "Wet Pows",
            value = weatherPresentationModel.wetBows
        )
        WeatherDetailItem(
            icon = painterResource(id = R.drawable.ic_wind),
            label = "Windy Fur",
            value = weatherPresentationModel.windSpeed
        )
        WeatherDetailItem(
            icon = painterResource(id = R.drawable.ic_thunder_storm),
            label = "Cloud",
            value = weatherPresentationModel.cloud
        )
    }
}

@Composable
fun WeatherDetailItem(icon: Painter, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun WeatherForecastCard(item: WeatherPresentationModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(focusedElevation = 4.dp, defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = CardDefaults.outlinedCardBorder(enabled = true)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.dayName, style = MaterialTheme.typography.bodyLarge, color = Color.White)
            AsyncImage(
                model = item.iconUrl,
                contentDescription = "Thunderstorm",
                modifier = Modifier.size(24.dp)
            )
            Text(text = item.temperatureStatus.status, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = item.temperatureDegrees, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}