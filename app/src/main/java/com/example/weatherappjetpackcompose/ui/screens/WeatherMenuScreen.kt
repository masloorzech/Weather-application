package com.example.weatherappjetpackcompose.ui.screens

import com.example.weatherappjetpackcompose.R
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherappjetpackcompose.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappjetpackcompose.data.managers.DataStoreManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.googlefonts.GoogleFont


@Composable
@Preview
fun WeatherMenuScreen() {
    var inter = FontFamily(Font(R.font.inter))
    var isChecked = true;
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181820))
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ){

        }
        Column(
            modifier = Modifier
                .background(color = Color(0xFF2F2C37), shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            //Option + Text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(12.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.slider),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Text("Options"
                    , modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 24.sp,
                        color = Color(0xFFD2D1D3),
                        fontFamily = inter
                    )

                }
            }
            //Line
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF181820))
                    .height(1.dp)
            ){}

            //First section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text("Units controls"
                    , modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    fontSize = 24.sp,
                    color = Color(0xFFD2D1D3),
                    fontFamily = inter
                )
                Text("Select units to show in the main menu. You can switch between Celsius and Fahrenheit, as well as between km/h and m/s for wind speed."
                    , modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp, horizontal = 12.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 12.sp,
                    color = Color(0xFF7E7F8D),
                    fontFamily = inter
                )
                    //Tempearture section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 0.dp, horizontal = 12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Arrangement.Center,
                            Alignment.CenterVertically
                        ){
                            Text(
                                "°C",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Right,
                                fontSize = 9.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter,
                            )
                            Switch(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                checked = isChecked,
                                onCheckedChange = { isChecked = it }

                            )
                            Text(
                                "°F",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Left,
                                fontSize = 9.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter
                            )

                        }
                        Text(
                            "Temperature",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = Color(0xFF7E7F8D),
                            fontFamily = inter
                        )

                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Arrangement.Center,
                            Alignment.CenterVertically
                            ){
                            Text(
                                "km/h",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Right,
                                fontSize = 9.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter,
                            )
                        Switch(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            checked = isChecked,
                            onCheckedChange = { isChecked = it }

                        )
                            Text(
                                "m/s",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Left,
                                fontSize = 9.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter
                            )

                            }
                        Text(
                            "Wind",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = Color(0xFF7E7F8D),
                            fontFamily = inter
                        )

                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF181820))
                    .height(1.dp)
            ){}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(
                    "Refresh rate", modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    fontSize = 24.sp,
                    color = Color(0xFFD2D1D3),
                    fontFamily = inter
                )
                Text(
                    "Choose a refresh interval that fits your needs – more frequent updates mean fresher data, but may use more battery.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp, horizontal = 12.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 12.sp,
                    color = Color(0xFF7E7F8D),
                    fontFamily = inter
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(1.dp, Color.Cyan)
                ){
                }
            }
        }

    }
}