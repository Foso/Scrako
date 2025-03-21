package de.jensklingenberg.scrako.common

import java.util.UUID

data class ScratchList(val name: String, val contents: List<Any>) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}

