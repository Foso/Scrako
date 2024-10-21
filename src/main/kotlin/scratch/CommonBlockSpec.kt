package me.jens.scratch

import kotlinx.serialization.json.JsonArray
import me.jens.scratch.common.Node

interface CommonBlockSpec : Node {
    val opcode: String
    val inputs: Map<String, JsonArray>
    val fields: Map<String, List<String?>>
    val shadow: Boolean
    val x: Int?
    val y: Int?
}