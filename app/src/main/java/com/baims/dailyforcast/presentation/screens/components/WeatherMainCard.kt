package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.baims.dailyforcast.R
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel

@Composable
fun WeatherMainCard(weatherPresentationModel: WeatherPresentationModel) {
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
fun WeatherDetailsCard(weatherPresentationModel: WeatherPresentationModel) {
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
