package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.fitnessflowapp.ui.components.SetupPageLayout


@Composable
fun AgeScreen(
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    var age by rememberSaveable { mutableIntStateOf(28) }

    SetupPageLayout(
        title = title,
        description = description,
        onBack = onBack,
        onNext = onNext
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("How Old Are You?", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(20.dp))

            NumberPicker(
                value = age,
                range = 15..100,
                onValueChange = { age = it },
                dividersColor = MaterialTheme.colorScheme.primary,
                textStyle = MaterialTheme.typography.headlineLarge
            )
            HorizontalNumberPicker(
                selectedValue = age,
                range = 40..150,
                onValueChange = { age = it }
            )
        }
    }
}


@Composable
fun HorizontalNumberPicker(
    selectedValue: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(range.toList()) { value ->
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clickable { onValueChange(value) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value.toString(),
                    style = if (value == selectedValue)
                        MaterialTheme.typography.headlineLarge
                    else
                        MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                )
            }
        }
    }
}

