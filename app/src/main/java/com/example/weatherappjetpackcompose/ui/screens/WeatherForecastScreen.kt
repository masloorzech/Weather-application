import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.weatherappjetpackcompose.ui.screens.WeatherScreen
import com.example.weatherappjetpackcompose.ui.screens.formatUnixTime
import com.example.weatherappjetpackcompose.ui.theme.Purple40
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel

@Composable
fun WeatherForecastScreen(viewModel: WeatherViewModel = viewModel()) {
    val forecastList = viewModel.forecast.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp,horizontal = 16.dp)
    ) {
        items(forecastList) { forecast ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Purple40)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("📅 Data: ${formatUnixTime(forecast.dt)}")
                        Text("🌡️ Temp: ${forecast.main.temp}°C")
                        Text("📖 Opis: ${forecast.weather[0].description}")
                    }
                    Image(
                        painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png"),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
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