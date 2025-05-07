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
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

val Bungee = FontFamily(
    Font(R.font.bungee_regular, FontWeight.Normal)
)

// Enum class for font weight
enum class FontWeightOption(val weight: FontWeight, val displayName: String) {
    NORMAL(FontWeight.Normal, "Normal"),
    MEDIUM(FontWeight.Medium, "Medium"),
    SEMI_BOLD(FontWeight.SemiBold, "Semi Bold"),
    BOLD(FontWeight.Bold, "Bold")
}

// Tạo Typography dựa trên font size và font weight
fun createTypography(fontSize: Int, fontWeight: FontWeight): Typography {
    val baseTextStyle = TextStyle(
        fontFamily = Montserrat,
        fontWeight = fontWeight,
        lineHeight = (fontSize * 1.5).sp,
        letterSpacing = 0.sp
    )

    return Typography(
        bodyLarge = baseTextStyle.copy(
            fontSize = fontSize.sp,
            letterSpacing = 0.15.sp,
            lineHeight = (fontSize * 1.5).sp
        ),
        bodyMedium = baseTextStyle.copy(
            fontSize = (fontSize - 2).sp,
            letterSpacing = 0.25.sp,
            lineHeight = (fontSize * 1.4).sp
        ),
        bodySmall = baseTextStyle.copy(
            fontSize = (fontSize - 4).sp,
            letterSpacing = 0.4.sp,
            lineHeight = (fontSize * 1.3).sp
        ),
        titleLarge = baseTextStyle.copy(
            fontSize = (fontSize + 8).sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
            lineHeight = (fontSize * 1.2).sp
        ),
        titleMedium = baseTextStyle.copy(
            fontSize = (fontSize + 4).sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
            lineHeight = (fontSize * 1.3).sp
        ),
        titleSmall = baseTextStyle.copy(
            fontSize = (fontSize + 2).sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
            lineHeight = (fontSize * 1.4).sp
        ),

        // Label styles - balanced letter spacing
        labelLarge = baseTextStyle.copy(
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            lineHeight = (fontSize * 1.4).sp
        ),
        labelMedium = baseTextStyle.copy(
            fontSize = (fontSize - 2).sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.15.sp,
            lineHeight = (fontSize * 1.3).sp
        ),
        labelSmall = baseTextStyle.copy(
            fontSize = (fontSize - 4).sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
            lineHeight = (fontSize * 1.2).sp
        ),

        // Additional styles for specific use cases
        headlineLarge = baseTextStyle.copy(
            fontSize = (fontSize + 12).sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp,
            lineHeight = (fontSize * 1.1).sp
        ),
        headlineMedium = baseTextStyle.copy(
            fontSize = (fontSize + 8).sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.25).sp,
            lineHeight = (fontSize * 1.2).sp
        ),
        headlineSmall = baseTextStyle.copy(
            fontSize = (fontSize + 4).sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.sp,
            lineHeight = (fontSize * 1.3).sp
        )
    )
}

// Default Typography object
val Typography = createTypography(16, FontWeight.Normal)