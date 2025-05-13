package com.example.fitnessflowapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.fitnessflowapp.R

//nieco si pozriet s tymito farbami ze ako co teda ci val alebo fun composoable

//default
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

//light mode
val LightBlack = Color(0xFF232323)
val Grey1 = Color(0xFF7B6F72)
val Grey2 = Color(0xFFADA4A5)
val Tan = Color(0xFFC9A690)
val PapayaWhip = Color(0xFFFFEECF)

//default
@Composable
fun AppBlack(): Color = colorResource(id = R.color.black)

@Composable
fun AppWhite(): Color = colorResource(id = R.color.white)

//light mode
@Composable
fun AppLightBlack(): Color = colorResource(id = R.color.lightBlack)

@Composable
fun AppGrey1(): Color = colorResource(id = R.color.grey_1)

@Composable
fun AppGrey2(): Color = colorResource(id = R.color.grey_2)

@Composable
fun appTan(): Color = colorResource(id = R.color.tan)

@Composable
fun AppPapayaWhip(): Color = colorResource(id = R.color.papayaWhip)

//dark mode