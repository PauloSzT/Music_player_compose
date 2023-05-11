package com.android.example.music.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.example.music.R

val NotoSerif = FontFamily(
    Font(R.font.notoserifregular, FontWeight.Normal),
    Font(R.font.notoserifbold, FontWeight.Bold,),
    Font(R.font.notoserifitalic, FontWeight.Light, FontStyle.Italic)
)


val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = NotoSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = NotoSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NotoSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NotoSerif,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp
    )
)