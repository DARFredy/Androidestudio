// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // 🧩 Si estás usando Compose con Kotlin 2.0+, deja este plugin:
    alias(libs.plugins.kotlin.compose) apply false
}

// ✅ No agregues otros plugins personalizados aquí.
// Este archivo solo se encarga de declarar los plugins globales,
// la configuración real de tu app está en build.gradle (Module: app).
