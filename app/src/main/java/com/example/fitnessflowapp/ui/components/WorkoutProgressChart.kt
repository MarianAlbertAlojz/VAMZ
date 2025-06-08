package com.example.fitnessflowapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessflowapp.R
//strings ok
//komentare
@Composable
fun WorkoutProgressChart(
    data: List<Float>,
    selectedDayIndex: Int = 4,
    workoutName: String = stringResource(R.string.upperbody_workout_title)
) {
    val maxPercentage = 100f
    val context = LocalContext.current

    val days = listOf(
        context.getString(R.string.day_sun),
        context.getString(R.string.day_mon),
        context.getString(R.string.day_tue),
        context.getString(R.string.day_wed),
        context.getString(R.string.day_thu),
        context.getString(R.string.day_fri),
        context.getString(R.string.day_sat)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.workout_progress_title),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD7B59F)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(text = stringResource(R.string.workout_progress_button))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val widthStep = size.width / (data.size - 1)
                val heightRatio = size.height / maxPercentage

                for (i in 0 until data.lastIndex) {
                    val startX = i * widthStep
                    val endX = (i + 1) * widthStep
                    val startY = size.height - (data[i] * heightRatio)
                    val endY = size.height - (data[i + 1] * heightRatio)

                    drawLine(
                        color = Color(0xFFCE9B7C),
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 4f
                    )
                }

                val barX = selectedDayIndex * widthStep
                val barHeight = size.height - (data[selectedDayIndex] * heightRatio)
                drawRect(
                    color = Color(0xFFD7B59F),
                    topLeft = Offset(barX - 10.dp.toPx(), barHeight),
                    size = Size(20.dp.toPx(), size.height - barHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            days.forEachIndexed { index, day ->
                Text(
                    text = day,
                    fontSize = 12.sp,
                    fontWeight = if (index == selectedDayIndex) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == selectedDayIndex) Color(0xFFD7B59F) else Color.Gray
                )
            }
        }
    }
}

