package com.southerntw.safespace.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.southerntw.safespace.R

val Roboto = FontFamily(
    Font(R.font.roboto_blackitalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.roboto_thinitalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.roboto_black, FontWeight.Bold),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

val Urbanist = FontFamily(
    Font(R.font.urbanist_bold, FontWeight.Bold),
    Font(R.font.urbanist_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.urbanist_regular, FontWeight.Normal),
    Font(R.font.urbanist_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.urbanist_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.urbanist_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.urbanist_extrabold, FontWeight.ExtraBold),
    Font(R.font.urbanist_light, FontWeight.Light),
    Font(R.font.urbanist_semibold, FontWeight.SemiBold),
    Font(R.font.urbanist_blackitalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.urbanist_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.urbanist_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.urbanist_extralight, FontWeight.ExtraLight),
    Font(R.font.urbanist_medium, FontWeight.Medium),
    Font(R.font.urbanist_thinitalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.urbanist_thin, FontWeight.Thin)
)



// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    labelMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
)