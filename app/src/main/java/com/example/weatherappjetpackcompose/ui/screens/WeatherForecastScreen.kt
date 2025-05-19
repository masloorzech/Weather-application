import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.R
import com.example.weatherappjetpackcompose.ui.components.SmallPanel
import com.example.weatherappjetpackcompose.ui.components.TextField
import com.example.weatherappjetpackcompose.ui.screens.formatUnixTime
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel

@Composable
fun WeatherForecastScreen(viewModel: WeatherViewModel = viewModel()) {
    val forecastList = viewModel.forecast.collectAsState().value

    //Read temperature unif from sharedprefs

    var isCelsius by remember { mutableStateOf(true) }
    val temperatureUnitString = if (isCelsius) "°C" else "°F"

    //Read city from shared prefs


    var pixel_font = FontFamily(Font(R.font.pixel_sans))

    val groupedForecast = forecastList.groupBy { item ->
        java.time.Instant.ofEpochSecond(item.dt)
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate()
            .toString()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 32.dp,horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.5f),
            contentAlignment = Alignment.Center){
            Text("ClimaSynth",
                fontSize = 42.sp,
                color = Color(0xFFD2D1D3),
                fontFamily = pixel_font
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.1f)
        )

        LazyColumn(
            modifier = Modifier
                .weight(6.4f)
                .padding(horizontal = 16.dp)
        ) {

            items(groupedForecast.entries.toList()) { (date, forecasts) ->
                    TextField(text = "$date")

                    val firstRow = forecasts.take(4)
                    val secondRow = forecasts.drop(4).take(4)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        firstRow.forEach { forecast ->
                            Card(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .height(150.dp)
                                    .weight(1f),
                            ) {
                                val temp =
                                    if (isCelsius) forecast.main.temp else (forecast.main.temp * 9 / 5) + 32
                                SmallPanel(
                                    time = formatUnixTime(forecast.dt).split(" ").last(),
                                    temperature = temp.toString(),
                                    unit = temperatureUnitString,
                                    imageRes = "https://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"
                                )
                            }
                        }
                    }
                if (secondRow.size==4) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        secondRow.forEach { forecast ->
                            Card(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .height(150.dp)
                                    .weight(1f),
                            ) {
                                val temp =
                                    if (isCelsius) forecast.main.temp else (forecast.main.temp * 9 / 5) + 32
                                SmallPanel(
                                    time = formatUnixTime(forecast.dt).split(" ").last(),
                                    temperature = temp.toString(),
                                    unit = temperatureUnitString,
                                    imageRes = "https://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherForecastScreen() {
    WeatherForecastScreen()
}