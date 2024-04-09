package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val markaziTextFamily = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Normal)
)

val karlaTextFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 48.sp
    ),
    titleMedium = TextStyle(
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    displayMedium = TextStyle(
        fontFamily = karlaTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    displaySmall = TextStyle(
        fontFamily = karlaTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = karlaTextFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = karlaTextFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = karlaTextFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)