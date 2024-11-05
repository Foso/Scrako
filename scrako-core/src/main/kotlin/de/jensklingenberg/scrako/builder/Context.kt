package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.ScratchList
import java.util.UUID

data class Context(
    val variableMap: Map<String, UUID>,
    val lists: Map<String, ScratchList>,
    val functions: List<Argumenti>,
    val broadcastMap: Map<String, String>
)