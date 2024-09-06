package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel

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