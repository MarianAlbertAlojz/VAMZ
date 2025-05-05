package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fitnessflowapp.ui.components.SetupPageLayout

@Composable
fun GenderScreen(
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

        }
    }
}

