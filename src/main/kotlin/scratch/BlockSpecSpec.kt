package me.jens.scratch

import kotlinx.serialization.json.JsonArray
import scratch.Block
import scratch.CommonBlockSpec
import java.util.UUID

open class BlockSpecSpec(
    override val opcode: String,
    override val inputs: Map<String, JsonArray> = emptyMap(),
    override val fields: Map<String, List<String?>> = emptyMap(),
    override val shadow: Boolean = false,
    override val x: Int? = null,
    override val y: Int? = null,
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

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        listIndex: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {
        // val name = "${listIndex}_${layer}_$index"
        val newNext = nextUUID?.toString()
        visitors[name.toString()] = toBlock(newNext, parent, layer == 0 && index == 0)
    }
}