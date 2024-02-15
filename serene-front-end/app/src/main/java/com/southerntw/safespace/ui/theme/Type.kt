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
    Font(R.font.Roboto_BlackItalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.Roboto_LightItalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.Roboto_ThinItalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.Roboto_Black, FontWeight.Bold),
    Font(R.font.Roboto_Light, FontWeight.Light),
    Font(R.font.Roboto_Thin, FontWeight.Thin),
    Font(R.font.Roboto_BoldItalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.Roboto_MediumItalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.Roboto_Bold, FontWeight.Bold),
    Font(R.font.Roboto_Medium, FontWeight.Medium),
    Font(R.font.Roboto_Italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.Roboto_Regular, FontWeight.Normal)
)

val Urbanist = FontFamily(
    Font(R.font.Urbanist_Bold, FontWeight.Bold),
    Font(R.font.Urbanist_Italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.Urbanist_Regular, FontWeight.Normal),
    Font(R.font.Urbanist_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.Urbanist_LightItalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.Urbanist_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.Urbanist_ExtraBold, FontWeight.ExtraBold),
    Font(R.font.Urbanist_Light, FontWeight.Light),
    Font(R.font.Urbanist_SemiBold, FontWeight.SemiBold),
    Font(R.font.Urbanist_BlackItalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.Urbanist_ExtraLightItalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.Urbanist_MediumItalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.Urbanist_ExtraLight, FontWeight.ExtraLight),
    Font(R.font.Urbanist_Medium, FontWeight.Medium),
    Font(R.font.Urbanist_ThinItalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.Urbanist_Thin, FontWeight.Thin)
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
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)