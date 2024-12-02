package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.ScratchList
import java.util.UUID

data class Context internal constructor(
    val variableMap: Map<String, UUID>,
    val lists: Map<String, ScratchList>,
    val functions: List<ArgumentDataHolder>,
    val broadcastMap: Map<String, String>,
    val inputPath: String,
    val layer: Int = 0
)