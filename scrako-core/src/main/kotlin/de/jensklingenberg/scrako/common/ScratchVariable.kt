package de.jensklingenberg.scrako.common

import java.util.UUID


class ScratchVariable(val name: String) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}

fun ScriptBuilder.getVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    variables.add(element)
    return element
}