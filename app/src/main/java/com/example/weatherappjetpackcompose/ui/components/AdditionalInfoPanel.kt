package com.example.weatherappjetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdditionalInfoPanel(
    value: String,
    unit: String,
    imageRes: Int,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFF2F2C37), shape = RoundedCornerShape(8.dp))
    ){
        Box(modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center)
            {
                Image(painter = painterResource(id = imageRes),
                    contentDescription = "Icon",
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(Color(0xFFD2D1D3)))
        }
        Box(modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(3f)
                .padding(vertical = 16.dp)
                .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ){
            Text("$value",
                fontSize = 48.sp,
                color = Color(0xFFD2D1D3)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 16.dp)
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(color = Color(0xFF4C4857), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ){
                Text("$unit",
                    fontSize = 24.sp,
                    color = Color(0xFFD2D1D3))
            }

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.1f))

    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }