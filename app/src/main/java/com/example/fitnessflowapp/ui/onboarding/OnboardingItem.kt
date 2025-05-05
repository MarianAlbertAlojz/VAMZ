package com.example.fitnessflowapp.ui.onboarding

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.data.onboarding.OnboardingPage
import com.example.fitnessflowapp.ui.theme.Black
import com.example.fitnessflowapp.ui.theme.Tan
import com.example.fitnessflowapp.ui.theme.White


@Composable
fun OnboardingItem(
    page: OnboardingPage,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(411.dp)  //kvoli tomu aby text bol vzdy v rovnakej vyske
        ) {
            Image(
                painter = painterResource(id = page.imageBackground),
                contentDescription = null,
                contentScale = ContentScale.FillBounds, //vypln
                modifier = Modifier.matchParentSize()
            )
        }
        Spacer(modifier = Modifier.height(55.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = page.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Spacer(modifier = Modifier.height(105.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
            horizontalArrangement = Arrangement.End,

            ) {
            IconButton(
                onClick = onNextClick,
                modifier = Modifier.size(60.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = White,
                    contentColor = Tan,
                    disabledContainerColor = Tan.copy(alpha = 0f),
                    disabledContentColor = White.copy(alpha = 0f)
                )
            ) {
                Icon(
                    painter = painterResource(page.imageButton),
                    contentDescription = "Next"

                )
            }
        }


    }
}

