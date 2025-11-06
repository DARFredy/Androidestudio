package com.example.aplicacion.ui

import androidx.annotation.DrawableRes

data class Warrior(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val type: WarriorType
)

enum class WarriorType {
    SAMURAI,
    NINJA
}
