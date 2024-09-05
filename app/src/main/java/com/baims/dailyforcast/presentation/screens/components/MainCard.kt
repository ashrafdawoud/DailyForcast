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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import com.baims.dailyforcast.R

@Composable
fun MainCard(paddingValue: PaddingValues) {
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
        val cites = listOf("Cairo", "Benha", "Alex")
        DropdownList(
            items = cites,
            selected = cites.first(),
            onSelection = {}
        )
        WeatherMainCard()
        Spacer(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .height(0.5.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
        WeatherDetailsCard()
    }
}

@Composable
private fun DropdownList(
    items: List<String>,
    selected: String,
    onSelection: (String) -> Unit
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
                    text = selectedCity.value,
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
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
                        )
                    },
                    onClick = {
                        expanded.value = false
                        selectedCity.value = item
                    })
            }
        }
    }
}

@Composable
private fun WeatherMainCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sun icon
            Image(
                painter = painterResource(id = R.drawable.ic_sun), // Replace with your sun icon
                contentDescription = "Sun",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Tomorrow
            Text(
                text = "Tomorrow",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Temperature
            Text(
                text = "14°",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 50.sp),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Clear Sky
            Text(
                text = "Clear Sky",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.White
            )
        }
    }
}

@Composable
private fun WeatherDetailsCard() {
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
            value = "0%"
        )
        WeatherDetailItem(
            icon = painterResource(id = R.drawable.ic_wind),
            label = "Windy Fur",
            value = "45 km/h"
        )
        WeatherDetailItem(
            icon = painterResource(id = R.drawable.ic_thunder_storm),
            label = "Thunderstorm",
            value = "2%"
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
fun WeatherForecastCard() {
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
            Text(text = "Sun", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Image(
                painter = painterResource(id = R.drawable.ic_sun),
                contentDescription = "Thunderstorm",
                modifier = Modifier.size(24.dp)
            )
            Text(text = "Thunderstorm", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = "16°", style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}