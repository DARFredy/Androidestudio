package com.example.aplicacion.ui

import com.example.aplicacion.R

fun getWarriors(): List<Warrior> {
    return listOf(
        // Samuráis
        Warrior(1, "Guerrero de la Luna", "Un samurái que blande una hoja forjada con luz de luna.", R.drawable.samurai, WarriorType.SAMURAI),
        Warrior(2, "Maestro de la Lanza", "Un veterano de cien batallas, experto en el arte de la lanza.", R.drawable.samurai1, WarriorType.SAMURAI),
        Warrior(3, "Ronin Silencioso", "Un samurái errante que habla solo a través del filo de su espada.", R.drawable.samurai2, WarriorType.SAMURAI),
        Warrior(4, "Daimyō del Norte", "Un señor feudal de las frías tierras del norte.", R.drawable.samurai3, WarriorType.SAMURAI),
        Warrior(5, "General del Shogun", "El comandante supremo de los ejércitos del Shogun.", R.drawable.samurai4, WarriorType.SAMURAI),
        Warrior(6, "Guardián del Templo", "Un samurái que ha jurado proteger un templo sagrado.", R.drawable.samurai6, WarriorType.SAMURAI),
        Warrior(7, "Cazador de Demonios", "Un guerrero especializado en la caza de criaturas sobrenaturales.", R.drawable.samurai7, WarriorType.SAMURAI),
        Warrior(8, "Poeta de la Espada", "Un samurái que encuentra la belleza tanto en el verso como en el combate.", R.drawable.samurai8, WarriorType.SAMURAI),
        Warrior(9, "Vengador Escarlata", "Un guerrero solitario en una misión de venganza.", R.drawable.samurai9, WarriorType.SAMURAI),
        Warrior(10, "Heredero del Clan", "El joven líder de un poderoso clan samurái.", R.drawable.samurai10, WarriorType.SAMURAI),

        // Ninjas
        Warrior(11, "Ninja del Viento", "Un espía tan rápido y silencioso como el viento.", R.drawable.ninja1, WarriorType.NINJA),
        Warrior(12, "Ninja de la Sombra", "Un asesino que utiliza las sombras para ocultar su presencia.", R.drawable.ninja2, WarriorType.NINJA),
        Warrior(13, "Ninja del Fuego", "Un experto en explosivos y tácticas de distracción con fuego.", R.drawable.ninja4, WarriorType.NINJA),
        Warrior(14, "Naruto Uzumaki", "El ninja más impredecible y cabeza hueca de la Aldea de la Hoja.", R.drawable.naruto, WarriorType.NINJA),
        Warrior(15, "Ryu Hayabusa", "El legendario Súper Ninja, heredero del clan Hayabusa.", R.drawable.hayabusa, WarriorType.NINJA),
        Warrior(16, "Ninja Lobo", "Un shinobi que lucha con la ferocidad y astucia de una manada de lobos.", R.drawable.ninjalobo, WarriorType.NINJA),
        Warrior(17, "Ninja Rojo", "Un agente de élite reconocible por su distintiva máscara roja.", R.drawable.ninjarojo, WarriorType.NINJA),
        Warrior(18, "Ninja Demoníaco", "Un ninja que utiliza el miedo y las ilusiones para derrotar a sus enemigos.", R.drawable.ninjademoniaco, WarriorType.NINJA),
        Warrior(19, "Ninja Futurista", "Un guerrero de una era futura que combina tecnología y ninjutsu.", R.drawable.ninjafuturista, WarriorType.NINJA),
        Warrior(20, "Ninja del Sonido", "Un shinobi que utiliza el sonido como arma para desorientar y atacar.", R.drawable.ninja1, WarriorType.NINJA)
    )
}
