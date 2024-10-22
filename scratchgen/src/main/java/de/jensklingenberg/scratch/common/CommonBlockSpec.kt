package de.jensklingenberg.scratch.common

import kotlinx.serialization.json.JsonArray

interface CommonBlockSpec : Node {
    val opcode: String
    val inputs: Map<String, JsonArray>
    val fields: Map<String, List<String?>>
    val shadow: Boolean
    val x: Int?
    val y: Int?
}