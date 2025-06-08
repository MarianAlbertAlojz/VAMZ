package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.SetupPageLayout

//strings ok
//komentare

@Composable
fun WeightScreen(
    weight: Int,
    onWeightChanged: (Int) -> Unit,
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
            Text(title, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(20.dp))

            NumberPicker(
                value = weight,
                range = 15..100,
                onValueChange = onWeightChanged,
                dividersColor = MaterialTheme.colorScheme.primary,
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = stringResource(id = R.string.selected_weight, weight),
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

