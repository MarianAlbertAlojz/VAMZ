package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.data.model.ActivityLevel
import com.example.fitnessflowapp.ui.components.SetupPageLayout

//enum class ActivityLevel { Beginner, Intermediate, Advanced } // toto potom premysliet ze kde to dat

@Composable
fun PhysicalActivityLevelScreen(
    selectedLevel: ActivityLevel?,
    onLevelSelected: (ActivityLevel) -> Unit,
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    SetupPageLayout(
        title = title,
        description = description,
        onBack = onBack,
        onNext = onNext,
        isNextEnabled = selectedLevel != null
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ActivityLevel.entries.forEach { level ->
                val isSelected = level == selectedLevel
                Button(
                    onClick = { onLevelSelected(level) },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = if (isSelected) {
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                ) {
                    Text(
                        text = level.name,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
