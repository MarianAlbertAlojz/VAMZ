package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.data.model.Goal
import com.example.fitnessflowapp.ui.components.SetupPageLayout


@Composable
fun GoalScreen(
    selectedGoal: Goal?,
    onGoalSelected: (Goal) -> Unit,
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
        isNextEnabled = selectedGoal != null //povolit len ked sa zvoli
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Goal.entries.forEach { goal ->
                val isSelected = goal == selectedGoal
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(28.dp)
                        )
                        .clickable { onGoalSelected(goal) }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = goal.name.replaceCamelCase(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(
                                width = 2.dp,
                                color = if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurfaceVariant,
                                shape = CircleShape
                            )
                            .background(
                                color = if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Color.Transparent,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

private fun String.replaceCamelCase(): String =
    this.replace(Regex("([a-z])([A-Z])"), "$1 $2")
        .replaceFirstChar { it.uppercaseChar() }
