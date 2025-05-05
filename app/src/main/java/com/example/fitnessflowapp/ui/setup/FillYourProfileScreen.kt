package com.example.fitnessflowapp.ui.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessflowapp.ui.components.SetupPageLayout
import com.example.fitnessflowapp.ui.theme.Tan
import com.example.fitnessflowapp.ui.theme.White

@Composable
fun FillYourProfileScreen(
    title: String,
    description: String,
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    var fullName by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }

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
            Box {
                Image(
                    painter = , //toto vyriesit
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                IconButton(
                    onClick = ,// toto tiez
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-8).dp, y = (-8).dp)
                        .size(32.dp)
                        .background(White, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = ), // ikonu pridat
                        contentDescription = "Edit picture",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))


            ProfileTextField(
                label = "Full name",
                value = fullName,
                onValueChange = { fullName = it }
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label = "Nickname",
                value = nickname,
                onValueChange = { nickname = it }
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label = "Email",
                value = email,
                keyboardType = KeyboardType.Email,
                onValueChange = { email = it }
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label = "Mobile Number",
                value = mobileNumber,
                keyboardType = KeyboardType.Phone,
                onValueChange = { mobileNumber = it }
            )

            Spacer(Modifier.weight(1f))


            Button(
                onClick = ,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    "Start",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
private fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = White,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Tan,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}
