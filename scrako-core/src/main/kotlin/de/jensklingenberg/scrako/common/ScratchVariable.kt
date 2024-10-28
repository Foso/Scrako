package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.ScriptBuilder
import java.util.UUID


class ScratchVariable(val name: String) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}

fun ScriptBuilder.getVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    variables.add(element)
    return element
}