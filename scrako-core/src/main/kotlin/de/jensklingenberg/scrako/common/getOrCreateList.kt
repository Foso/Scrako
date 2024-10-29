package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.TargetBuilder

fun TargetBuilder.getOrCreateList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}