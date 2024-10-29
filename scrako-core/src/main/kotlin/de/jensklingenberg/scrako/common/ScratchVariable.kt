package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.TargetBuilder


class ScratchVariable(val name: String) : ReporterBlock

fun TargetBuilder.getOrCreateVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}