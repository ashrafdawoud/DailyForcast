package com.baims.dailyforcast.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baims.dailyforcast.presentation.screens.components.MainCard
import com.baims.dailyforcast.presentation.screens.components.WeatherForecastCard

@Composable
fun ForeCastHomeScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) { paddingValue ->
        LazyColumn {
            item {
                MainCard(paddingValue = paddingValue)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
            }
            items(10){
                WeatherForecastCard()
            }
        }
    }
}