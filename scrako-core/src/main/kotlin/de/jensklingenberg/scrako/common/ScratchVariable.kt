package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.ScriptBuilder
import java.util.UUID


class ScratchVariable(val name: String,val id: UUID = UUID.randomUUID()) : ReporterBlock {

}

fun ScriptBuilder.getOrCreateVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(element)
    return element
}