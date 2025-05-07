package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.ui.components.SetupPageLayout

@Composable
fun PhysicalActivityLevelScreen(
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    SetupPageLayout(
        title = title,
        description = description,
        onBack = onBack,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = onNext,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "Begginer",
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onNext,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "Intermediate",
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onNext,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "Advance",
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

