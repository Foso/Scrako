package me.jens.scratch

import kotlinx.serialization.json.JsonArray
import scratch.Block
import scratch.CommonBlockSpec

open class BlockSpecSpec(
    override val opcode: String,
    override val inputs: Map<String, JsonArray> = emptyMap(),
    override val fields: Map<String, List<String?>> = emptyMap(),
    override val shadow: Boolean = false,
    override val x: Int? = null,
    override val y: Int? = null,
    override val childBlocks: List<CommonBlockSpec> = emptyList()
) : CommonBlockSpec {
    fun toBlock(next: String?, parent: String?, topLevel: Boolean) = Block(
        opcode = opcode,
        next = next,
        parent = parent,
        inputs = inputs,
        fields = fields,
        shadow = shadow,
        topLevel = topLevel,
        x = x,
        y = y
    )

    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"
        visitors[name] = toBlock(next, parent, layer == 0 && index == 0)
    }
}