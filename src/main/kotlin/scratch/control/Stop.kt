package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.Block
import java.util.UUID

class Stop(private val option: StopOption) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        listIndex: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {

        if (nextUUID != null) {
            throw IllegalArgumentException("Stop block with All cannot have a next block")
        }
        val newNext = nextUUID?.toString()
        visitors[name.toString()] = BlockSpecSpec(
            opcode = OpCode.control_stop,
            fields = mapOf("STOP_OPTION" to listOf((option.s), null))
        ).toBlock(newNext, parent, layer == 0 && index == 0)
    }
}

enum class StopOption(val s: String) {
    ALL("all"), OTHER("other scripts in sprite"), THIS("this script")
}