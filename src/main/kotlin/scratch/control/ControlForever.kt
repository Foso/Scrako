package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import scratch.Block
import me.jens.scratch.OpCode
import scratch.Visitor
import scratch.createBlocks2
import scratch.createSubStack

class ControlForever(private val childs: List<Visitor>) : Visitor {

    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"

        val childBlocks =
            createBlocks2(childs, layer + 1, name)

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_forever,
            childBlocks = emptyList(),
            inputs = mapOf("SUBSTACK" to createSubStack(childBlocks.blocks.filterNot { it.value.opcode == OpCode.operator_add }.keys.first()))
        ).toBlock(next, parent, layer == 0 && index == 0)

        visitors.putAll(childBlocks.blocks)
    }
}


