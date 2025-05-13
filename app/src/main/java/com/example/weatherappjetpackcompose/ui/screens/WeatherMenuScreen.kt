package com.example.weatherappjetpackcompose.ui.screens

import com.example.weatherappjetpackcompose.R
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WeatherMenuScreen() {

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
                        fontFamily = FontFamily()
                    )

                }
            }
            //Line
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF181820))
                    .height(2.dp)
            ){

            }

        }

    }
}