package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.model.Sound

class Sprite(
    val name: String,
    val sounds: List<Sound>,
    val size: Int = 100,
)