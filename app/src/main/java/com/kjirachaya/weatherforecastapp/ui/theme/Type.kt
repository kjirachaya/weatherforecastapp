package com.kjirachaya.weatherforecastapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.kjirachaya.weatherforecastapp.R

private val fontProvider =
    GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs,
    )

val robotoFont =
    FontFamily(
        Font(googleFont = GoogleFont("Rubik"), fontProvider = fontProvider),
    )

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodySmall =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        headlineSmall =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                letterSpacing = 0.5.sp,
            ),
        headlineLarge =
            TextStyle(
                fontFamily = robotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                letterSpacing = 0.5.sp,
            ),
    )
