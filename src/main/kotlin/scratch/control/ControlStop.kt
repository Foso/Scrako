package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import scratch.Block
import me.jens.scratch.OpCode
import scratch.Visitor

class ControlStop() : Visitor {
    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_stop,
            fields = mapOf("STOP_OPTION" to listOf(("all"), null))
        ).toBlock(next, parent, layer == 0 && index == 0)
    }
}