package com.example.weatherappjetpackcompose.ui.screens
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherappjetpackcompose.viewmodel.CitiViewModel
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import androidx.compose.ui.platform.LocalFocusManager
import com.example.weatherappjetpackcompose.data.managers.SharedPreferencesHelper


@Preview
@Composable
fun CitySelectionScreen(
  cityViewModel: CitiViewModel = viewModel(),
  weatherViewModel: WeatherViewModel = viewModel()
) {


  val context = LocalContext.current
  val prefsHelper = SharedPreferencesHelper(context)

  val pixelFont = FontFamily(Font(R.font.pixel_sans))
  val cities by cityViewModel.favouriteCities.collectAsState()
  val suggestions by cityViewModel.autocompleteResults.collectAsState()
  var selectedCity by remember {mutableStateOf(prefsHelper.getSelectedCity())}
  var query by remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current
  var isFocused by remember { mutableStateOf(false) }


  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(0xFF181820))
      .padding(vertical = 32.dp, horizontal = 16.dp)
      .clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
      ) {
        focusManager.clearFocus()
        cityViewModel.clearSuggestions()
        },
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
      .padding(bottom = 8.dp))
    {
      TextField(
        value = query,
        onValueChange = {
          query = it
          cityViewModel.searchCities(query)
        },
        placeholder = {
          Text("Search City")
        },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.Gray
          )
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .background(
            color = Color(0xFF2F2C37),
            shape = RoundedCornerShape(16.dp)
          )
          .onFocusChanged { focusState ->
            isFocused = focusState.isFocused
          },
        colors = TextFieldDefaults.colors(
          focusedContainerColor = Color.Transparent,
          unfocusedContainerColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          focusedTextColor = Color.White,
          unfocusedTextColor = Color.LightGray,
          cursorColor = Color(0xFF6200EE)
        ),
        singleLine = true
      )
    }

    if (query.isNotBlank() && isFocused && suggestions.isNotEmpty()) {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .height(180.dp)
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
              modifier = Modifier.padding(8.dp)
            )
          }
        }
      }
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      items(cities) { city ->
        if (city == selectedCity){
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF4C4857), shape = RoundedCornerShape(12.dp))
              .clickable{
                weatherViewModel.updateCity(city)
                selectedCity = city
              }
              .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = city,
              color = Color.LightGray,
              fontSize = 18.sp,
              modifier = Modifier.weight(1f)
                .padding(8.dp)

            )
            IconButton(onClick = {
              cityViewModel.removeCity(city)
            }) {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                contentDescription = "Remove City",
                tint = Color.White
              )
            }
          }
        }else {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF2F2C37), shape = RoundedCornerShape(12.dp))
              .clickable{
                weatherViewModel.updateCity(city)
                selectedCity = city
              }
              .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = city,
              color = Color.LightGray,
              fontSize = 18.sp,
              modifier = Modifier.weight(1f)
                .padding(8.dp)
            )
            IconButton(onClick = {
              cityViewModel.removeCity(city)
            }) {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                contentDescription = "Remove City",
                tint = Color.White
              )
            }
          }
        }
        Spacer(
          modifier = Modifier
            .height(8.dp)
        )
      }
    }
  }
}

