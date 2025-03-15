package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.model.Costume2
import de.jensklingenberg.scrako.model.Sound2

data class Config(
    val visible: Boolean = true,
    val posX: Double = 0.0,
    val posY: Double = 0.0,
    val size: Double = 100.0,
    val direction: Double = 90.0,
    val costumes: Set<Costume2> = emptySet(),
    val sounds: Set<Sound2> = emptySet()
)