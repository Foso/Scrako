package de.jensklingenberg.scrako.model

import kotlinx.serialization.Serializable

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor> = emptyList(),
    val extensions: List<String> = emptyList(),
    val meta: Meta
)
