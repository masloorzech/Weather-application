package com.example.weatherappjetpackcompose.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherappjetpackcompose.viewmodel.CitiViewModel
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel

@Preview
@Composable
fun CitySelectionScreen(
  cityViewModel: CitiViewModel = viewModel(),
  weatherViewModel: WeatherViewModel = viewModel()
) {
  val pixelFont = FontFamily(Font(R.font.pixel_sans))
  val cities by cityViewModel.favouriteCities.collectAsState()
  val suggestions by cityViewModel.autocompleteResults.collectAsState()
  var selected_city by remember {mutableStateOf("${weatherViewModel.city.toString()}")}
  var query by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(0xFF181820))
      .padding(vertical = 32.dp, horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Box(
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .weight(0.3f),
      contentAlignment = Alignment.Center
    ) {
      Text(
        "ClimaSynth",
        fontSize = 42.sp,
        color = Color(0xFFD2D1D3),
        fontFamily = pixelFont
      )
    }
    Row(modifier = Modifier
      .fillMaxWidth()
      .weight(1f))
    {
      TextField(
        value = query,
        onValueChange = {
          query = it
          cityViewModel.searchCities(query)
        },
        label = {
          Text("Search City")
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(90.dp)
          .background(color = Color(0xFF2F2C37))
          .padding(bottom = 8.dp)
      )
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .weight(1f)
    ) {
      items(suggestions) { suggestion ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
              cityViewModel.addCity(suggestion.name)
              query = ""
            },
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "${suggestion.name}, ${suggestion.country}",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = pixelFont,
            modifier = Modifier.padding(8.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(cities) { city ->
        if (city == selected_city){
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(color = Color.Red)
              .padding(vertical = 4.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = city,
              color = Color.LightGray,
              fontSize = 18.sp,
              fontFamily = pixelFont,
              modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
              cityViewModel.removeCity(city)
            }) {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                contentDescription = "Remove City",
                tint = Color.Red
              )
            }
          }
        }else {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clickable{
                selected_city = city
                weatherViewModel.updateCity(city)
              } ,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = city,
              color = Color.LightGray,
              fontSize = 18.sp,
              fontFamily = pixelFont,
              modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
              cityViewModel.removeCity(city)
            }) {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                contentDescription = "Remove City",
                tint = Color.Red
              )
            }
          }
        }
      }
    }
  }
}

