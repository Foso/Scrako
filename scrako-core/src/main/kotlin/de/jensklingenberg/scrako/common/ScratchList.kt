package de.jensklingenberg.scrako.common

import java.util.UUID

class ScratchList(val name: String, val contents: List<String>) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}