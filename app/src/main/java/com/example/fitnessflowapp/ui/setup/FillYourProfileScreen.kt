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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.ui.components.SetupPageLayout
import com.example.fitnessflowapp.ui.theme.Tan
import com.example.fitnessflowapp.ui.theme.White
import com.example.fitnessflowapp.ui.viewmodel.ProfileField
import com.example.fitnessflowapp.ui.viewmodel.ProfileFormState

@Composable
fun FillYourProfileScreen(
    title: String,
    description: String,
    form: ProfileFormState,
    onEditPictureClick: () -> Unit,
    onFieldChanged: (ProfileField, String) -> Unit,
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
            Box {
                Image(
                    painter = painterResource(id = R.drawable.david),
                    contentDescription = "Profile picture",//tieto stringy niekam inak dat asi do res
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape)
                )
                IconButton(
                    onClick = onEditPictureClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (0).dp, y = (-8).dp)
                        .size(30.dp)
                        .background(White, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_edit),
                        contentDescription = "Edit picture",//tieto stringy niekam inak dat asi do res
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            ProfileTextField(
                label         = "Full name",//tieto stringy niekam inak dat asi do res
                value         = form.fullName,
                onValueChange = { onFieldChanged(ProfileField.FullName, it) }
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label         = "Nickname",//tieto stringy niekam inak dat asi do res
                value         = form.nickname,
                onValueChange = { onFieldChanged(ProfileField.Nickname, it) }
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label         = "Email",//tieto stringy niekam inak dat asi do res
                value         = form.email,
                onValueChange = { onFieldChanged(ProfileField.Email, it) },
                keyboardType  = KeyboardType.Email
            )
            Spacer(Modifier.height(16.dp))
            ProfileTextField(
                label         = "Mobile Number",//tieto stringy niekam inak dat asi do res
                value         = form.mobileNumber,
                onValueChange = { onFieldChanged(ProfileField.Phone, it) },
                keyboardType  = KeyboardType.Phone
            )
            Spacer(Modifier.weight(1f))

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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Tan,
                unfocusedContainerColor = Tan,
                disabledContainerColor = Tan.copy(alpha = 0.5f),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
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
