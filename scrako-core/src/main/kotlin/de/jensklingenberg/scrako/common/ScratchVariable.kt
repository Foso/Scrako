package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.TargetBuilder
import java.util.UUID


class ScratchVariable(val name: String,val id: UUID = UUID.randomUUID()) : ReporterBlock {

}

fun TargetBuilder.getOrCreateVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}