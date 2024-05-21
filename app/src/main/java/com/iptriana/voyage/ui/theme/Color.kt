package com.iptriana.voyage.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val voyage_caption = Color.DarkGray
val voyage_divider_color = Color.LightGray
private val voyage_red = Color(0xFFE30425)
private val voyage_white = Color.White
private val voyage_purple_700 = Color(0xFF720D5D)
private val voyage_purple_800 = Color(0xFF5D1049)
private val voyage_purple_900 = Color(0xFF4E0D3A)

val voyageColors = lightColorScheme(
    primary = voyage_purple_800,
    secondary = voyage_red,
    surface = voyage_purple_900,
    onSurface = voyage_white,
    inversePrimary = voyage_purple_700
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)
