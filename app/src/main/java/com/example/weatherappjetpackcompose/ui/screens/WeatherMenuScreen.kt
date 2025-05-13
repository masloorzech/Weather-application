package com.example.weatherappjetpackcompose.ui.screens

import android.annotation.SuppressLint
import com.example.weatherappjetpackcompose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.DropdownMenuItem
import com.example.weatherappjetpackcompose.MenuActivity

@SuppressLint("DefaultLocale")
@Composable
@Preview
fun WeatherMenuScreen() {

    var inter = FontFamily(Font(R.font.inter))
    var pixel_font = FontFamily(Font(R.font.pixel_sans))
    var temperature by remember { mutableStateOf(true) }
    var wind by remember { mutableStateOf(true) }

    var selectedHour by remember { mutableStateOf(0) }
    var expandedHour by remember { mutableStateOf(false) }

    var selectedMinute by remember { mutableStateOf(30) }
    var expandedMinute by remember { mutableStateOf(false) }

    val hours = (0..6 step 1).toList()
    val minutes = (15..45 step 15).toList()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181820))
            .padding(vertical = 64.dp, horizontal = 16.dp),

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1f)
                    .background(Color(0xFF2F2C37), shape = RoundedCornerShape(12.dp))
                    .clickable(onClick = {
                        (context as? MenuActivity)?.finish()
                    })
            ){
                Image(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back",
                )
            }
            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )
            Text("ClimaSynth",
                fontSize = 42.sp,
                color = Color(0xFFD2D1D3),
                fontFamily = pixel_font
            )
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
                Text(
                    "Units controls", modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    fontSize = 24.sp,
                    color = Color(0xFFD2D1D3),
                    fontFamily = inter
                )
                Text(
                    "Select units to show in the main menu. You can switch between Celsius and Fahrenheit, as well as between km/h and m/s for wind speed.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp, horizontal = 12.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
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
                        ) {
                            Text(
                                "°C",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Right,
                                fontSize = 12.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter,
                            )
                            Switch(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                checked = temperature,
                                onCheckedChange = { temperature = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFF11131A),
                                    uncheckedThumbColor = Color(0xFF11131A),
                                    checkedTrackColor = Color(0xFFB8BDD6),
                                    uncheckedTrackColor = Color(0xFFB8BDD6)
                                )
                            )
                            Text(
                                "°F",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Left,
                                fontSize = 12.sp,
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
                        ) {
                            Text(
                                "km/h",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Right,
                                fontSize = 12.sp,
                                color = Color(0xFF7E7F8D),
                                fontFamily = inter,
                            )
                            Switch(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFF11131A),
                                    uncheckedThumbColor = Color(0xFF11131A),
                                    checkedTrackColor = Color(0xFFB8BDD6),
                                    uncheckedTrackColor = Color(0xFFB8BDD6)
                                ),
                                checked = wind,
                                onCheckedChange = { wind = it }

                            )
                            Text(
                                "m/s",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Left,
                                fontSize = 12.sp,
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
                    fontSize = 14.sp,
                    color = Color(0xFF7E7F8D),
                    fontFamily = inter
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(1f)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxHeight(),
                            Arrangement.Center,
                            Alignment.CenterVertically
                        )
                        {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .fillMaxHeight()
                                    .padding(0.dp,25.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .background(
                                            Color(0xFFB8BDD6),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .fillMaxWidth()
                                        .weight(1f),
                                )
                                {
                                    Text(
                                        text = String.format("%02d", selectedHour),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { expandedHour = true },
                                        textAlign = TextAlign.Center
                                    )
                                    DropdownMenu(
                                        modifier = Modifier
                                            .background(color = Color(0xFF2F2C37)),
                                        expanded = expandedHour,
                                        onDismissRequest = { expandedHour = false }
                                    ) {
                                        hours.forEach { hour ->
                                            DropdownMenuItem(
                                                modifier = Modifier
                                                    .background(color = Color(0xFF2F2C37))
                                                    .height(32.dp),
                                                text = { Text(String.format("%02d", hour),  color = Color(0xFFD2D1D3),) },
                                                onClick = {
                                                    selectedHour = hour
                                                    expandedHour = false
                                                }
                                            )
                                        }
                                    }
                                }
                                Text(
                                    "Hours",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    color = Color(0xFF7E7F8D),
                                    fontFamily = inter
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .fillMaxHeight()
                                    .padding(0.dp,25.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .background(
                                            Color(0xFFB8BDD6),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .fillMaxWidth()
                                        .weight(1f),
                                ) {
                                    Text(
                                        text = String.format("%02d", selectedMinute),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { expandedMinute = true },
                                        textAlign = TextAlign.Center

                                    )
                                    DropdownMenu(
                                        modifier = Modifier
                                            .background(color = Color(0xFF2F2C37)),
                                        expanded = expandedMinute,
                                        onDismissRequest = { expandedMinute = false }
                                    ) {
                                        minutes.forEach { minute ->
                                            DropdownMenuItem(
                                                modifier = Modifier
                                                    .height(32.dp)
                                                .background(color = Color(0xFF2F2C37)),
                                               text = { Text(String.format("%02d", minute),
                                                   color = Color(0xFFD2D1D3)) },
                                                onClick = {
                                                    selectedMinute = minute
                                                    expandedMinute = false
                                                }

                                            )
                                        }
                                    }
                                }
                                Text(
                                    "Minutes",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    color = Color(0xFF7E7F8D),
                                    fontFamily = inter
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(1f)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("ⓘ Note: Frequent updates may increase data usage and battery consumption."
                            ,color = Color(0xFF7E7F8D),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp,0.dp,5.dp,0.dp),
                            textAlign = TextAlign.Justify,
                            fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
