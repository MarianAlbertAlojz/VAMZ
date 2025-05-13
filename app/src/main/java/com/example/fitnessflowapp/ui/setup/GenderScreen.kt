package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.SetupPageLayout

enum class Gender {
    Male, Female
}


@Composable
fun GenderScreen(
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val selectedGender = remember { mutableStateOf<Gender?>(null) }

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
            GenderOption(
                gender = Gender.Male,
                selected = selectedGender.value == Gender.Male,
                iconRes = R.drawable.male_icon,
                label = "Male",
                onClick = { selectedGender.value = Gender.Male }
            )
            GenderOption(
                gender = Gender.Female,
                selected = selectedGender.value == Gender.Female,
                iconRes = R.drawable.female_icon,
                label = "Female",
                onClick = { selectedGender.value = Gender.Female }
            )
        }
    }
}


@Composable
fun GenderOption(
    gender: Gender,
    selected: Boolean,
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    color = if (selected) Color(0xFFD7B59D) else Color.Transparent,
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = if (selected) Color(0xFFFFF3DC) else Color(0xFFD7B59D),
                modifier = Modifier.size(48.dp)
            )
        }
        androidx.compose.material3.Text(
            text = label,
            color = Color.White
        )
    }
}


