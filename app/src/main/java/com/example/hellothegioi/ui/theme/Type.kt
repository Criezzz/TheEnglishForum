package com.example.hellothegioi.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.hellothegioi.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

val Bungee = FontFamily(
    Font(R.font.bungee_regular, FontWeight.Normal)
)
// Enum class cho font weight
enum class FontWeightOption(val weight: FontWeight, val displayName: String) {
    NORMAL(FontWeight.Normal, "Normal"),
    MEDIUM(FontWeight.Medium, "Medium"),
    SEMI_BOLD(FontWeight.SemiBold, "Semi Bold"),
    BOLD(FontWeight.Bold, "Bold")
}

// Tạo Typography dựa trên font size và font weight
fun createTypography(fontSize: Int, fontWeight: FontWeight): Typography {
    val baseTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = fontWeight,
        lineHeight = (fontSize * 1.5).sp,
        letterSpacing = 0.5.sp
    )

    return Typography(
        bodyLarge = baseTextStyle.copy(fontSize = fontSize.sp),
        bodyMedium = baseTextStyle.copy(fontSize = (fontSize - 2).sp),
        bodySmall = baseTextStyle.copy(fontSize = (fontSize - 4).sp),

        titleLarge = baseTextStyle.copy(
            fontSize = (fontSize + 8).sp,
            fontWeight = if (fontWeight == FontWeight.Bold) FontWeight.Bold else FontWeight.SemiBold
        ),
        titleMedium = baseTextStyle.copy(
            fontSize = (fontSize + 4).sp,
            fontWeight = if (fontWeight == FontWeight.Bold) FontWeight.Bold else FontWeight.SemiBold
        ),
        titleSmall = baseTextStyle.copy(
            fontSize = (fontSize + 2).sp,
            fontWeight = if (fontWeight == FontWeight.Bold) FontWeight.Bold else FontWeight.SemiBold
        ),

        labelLarge = baseTextStyle.copy(fontSize = fontSize.sp),
        labelMedium = baseTextStyle.copy(fontSize = (fontSize - 2).sp),
        labelSmall = baseTextStyle.copy(fontSize = (fontSize - 4).sp)
    )
}

// Đối tượng Typography mặc định
val Typography = createTypography(16, FontWeight.Normal)