package com.example.fitnessflowapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.fitnessflowapp.R

private val DarkColorScheme = darkColorScheme(
   // primary = Purple80,
   // secondary = PurpleGrey80,
    //tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Tan,
    secondary = PapayaWhip,
    onSecondary = LightBlack,
    tertiary = Grey1,
    onTertiary = Grey2,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun FitnessFlowAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(
            //primary = colorResource(R.color.primary_dark),
            //secondary = colorResource(R.color.secondary_dark),
            //tertiary = colorResource(R.color.tertiary_dark)
        )

        else -> lightColorScheme(
            primary = colorResource(R.color.tan),//roztriedit secondary a on secondary podla ich funkcie
            secondary = colorResource(R.color.papayaWhip),
            onSecondary = colorResource(R.color.lightBlack),
            tertiary = colorResource(R.color.grey_1),
            onTertiary = colorResource(R.color.grey_2),
            background = colorResource(R.color.white),
            surface = colorResource(R.color.black)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}