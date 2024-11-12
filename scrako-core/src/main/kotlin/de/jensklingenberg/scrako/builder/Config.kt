package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.model.Costume2
import de.jensklingenberg.scrako.model.Sound2

data class Config(
    val visible: Boolean = true,
    val posX: Double = 0.0,
    val posY: Double = 0.0,
    val size: Double = 100.0,
    val direction: Double = 90.0,
    val costumes: List<Costume2> = emptyList(),
    val sounds : List<Sound2> = emptyList()
)