package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baims.dailyforcast.R
import com.baims.dailyforcast.domain.model.CityModel

@Composable
fun DropdownList(
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