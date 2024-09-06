package com.baims.dailyforcast.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.presentation.screens.components.MainCard
import com.baims.dailyforcast.presentation.screens.components.WeatherForecastCard
import com.baims.dailyforcast.presentation.screens.model.event.ForeCastEvent
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel

@Composable
fun ForeCastHomeScreen(foreCastViewModel: ForeCastViewModel = hiltViewModel()) {

    val weatherPresentationModel by foreCastViewModel.weatherPresentationModel.collectAsStateWithLifecycle()
    val cites by foreCastViewModel.cites.collectAsStateWithLifecycle()
    val isFromCache by foreCastViewModel.isFromCache.collectAsStateWithLifecycle()
    val isLoading by foreCastViewModel.isLoading.collectAsStateWithLifecycle()
    val isError by foreCastViewModel.isError.collectAsStateWithLifecycle()
    val selectedCity = remember { mutableStateOf<CityModel>(CityModel()) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        snackbarHost = {
            if (isFromCache)
                Text(
                    text = "These Data from Cache and not accurate",
                    modifier = Modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
        }
    ) { paddingValue ->
        Box(contentAlignment = Alignment.Center) {
            LazyColumn {
                item {
                    MainCard(
                        weatherPresentationModel = weatherPresentationModel.firstOrNull()
                            ?: WeatherPresentationModel(),
                        cites = cites,
                        paddingValue = paddingValue,
                        onCitySelect = { city ->
                            selectedCity.value = city
                            foreCastViewModel.onTriggerEvent(ForeCastEvent.OnSelectCity(city))
                        }
                    )
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                }
                items(weatherPresentationModel) { item ->
                    WeatherForecastCard(item)
                }
            }
            if (isLoading)
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color.White
                )
            if (isError)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Something went wrong \n Retry Now",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                            .clickable {
                                foreCastViewModel.onTriggerEvent(ForeCastEvent.OnSelectCity(selectedCity.value))
                            },
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
        }
    }
}