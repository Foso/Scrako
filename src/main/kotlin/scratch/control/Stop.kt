package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import scratch.Block
import me.jens.scratch.OpCode
import scratch.Visitor

class Stop : Visitor {
    override fun visit(
        visitors: MutableMap<String, Block>,
        layer: Int,
        parent: String?,
        index: Int,
        next: Boolean,
        listIndex: Int
    ) {
        val name = "${listIndex}_${layer}_$index"
        val newNext = if (!next) null else "${listIndex}_${layer}_${index + 1}"
        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_stop,
            fields = mapOf("STOP_OPTION" to listOf(("all"), null))
        ).toBlock(newNext, parent, layer == 0 && index == 0)
    }
}