package com.example.aplicacion.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.aplicacion.RojoSangre
import com.example.aplicacion.GrisFondo
import com.example.aplicacion.GrisAcero
import com.example.aplicacion.BlancoHueso

private val DarkColorScheme = darkColorScheme(
    primary = RojoSangre,
    secondary = GrisAcero,
    background = GrisFondo,
    surface = GrisFondo,
    onPrimary = BlancoHueso,
    onSecondary = BlancoHueso,
    onBackground = BlancoHueso,
    onSurface = BlancoHueso,
)

private val LightColorScheme = lightColorScheme(
    primary = RojoSangre,
    secondary = GrisAcero,
    background = GrisFondo,
    surface = GrisFondo,
    onPrimary = BlancoHueso,
    onSecondary = BlancoHueso,
    onBackground = BlancoHueso,
    onSurface = BlancoHueso,
)

@Composable
fun GuerrerosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled to enforce the custom theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
