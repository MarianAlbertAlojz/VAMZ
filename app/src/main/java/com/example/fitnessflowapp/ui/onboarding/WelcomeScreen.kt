package com.example.fitnessflowapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessflowapp.ui.theme.Black
import com.example.fitnessflowapp.ui.theme.White


@Composable
fun WelcomeScreen(
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") } // toto potom riesit

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(top = 250.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),

            //horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), // prehodnotit
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Fitness",
                color = Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Flow",
                color = White,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 280.dp),
            //horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), toto prehodnotit
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Everybody Can Train",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,

                )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            //horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), toto prehodnotit
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center

        ) {
            Button(
                onClick = onGetStartedClick,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White
                ),
                modifier = Modifier
                    .height(60.dp)
                    .width(315.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontWeight = FontWeight.Bold,
                    color = Black,
                )
            }
        }

    }
}

@Preview
@Composable
fun SelectOptionPreview() {

}