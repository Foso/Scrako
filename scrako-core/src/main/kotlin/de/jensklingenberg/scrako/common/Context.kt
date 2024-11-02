package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.Argumenti
import java.util.UUID

data class Context(
    val variableMap: Map<String, UUID>,
    val lists: Map<String, ScratchList>,
    val functions: List<Argumenti>,
    val broadcastMap: Map<String, String>
)