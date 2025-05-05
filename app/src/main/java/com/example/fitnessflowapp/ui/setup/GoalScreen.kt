package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.ui.components.SetupPageLayout
import com.example.fitnessflowapp.ui.theme.Black
import com.example.fitnessflowapp.ui.theme.Tan

@Composable
fun GoalScreen(
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    val goals = listOf(
        "Lose Weight",
        "Gain Weight",
        "Muscle Mass Gain",
        "Shape Body",
        "Others"
    )
    var selected by remember { mutableStateOf<String?>(null) }

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
            GoalsList(
                goals = goals,
                selectedGoal = selected,
                onSelectGoal = { selected = it }
            )

            Spacer(Modifier.weight(1f))

        }
    }
}


@Composable
fun GoalsList(
    goals: List<String>,
    selectedGoal: String?,
    onSelectGoal: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        goals.forEach { goal ->
            GoalOption(
                text     = goal,
                selected = (goal == selectedGoal),
                onClick  = { onSelectGoal(goal) }
            )
        }
    }
}

@Composable
fun GoalOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Tan)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = Black
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 2.dp,
                    color = if (selected) Black else Color.Gray,
                    shape = CircleShape
                )
                .background(
                    color = if (selected) Black else Color.Transparent,
                    shape = CircleShape
                )
        )
    }
}

