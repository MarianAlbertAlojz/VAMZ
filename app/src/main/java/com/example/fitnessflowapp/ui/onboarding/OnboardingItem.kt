package com.example.fitnessflowapp.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun OnboardingItem(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = page.imageBackground),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = page.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = page.description, style = MaterialTheme.typography.bodyMedium)
        Image(
            painter = painterResource(id = page.imageButton),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
                .
        )
    }
}

