package de.jensklingenberg.scratch.control


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class Stop(private val option: StopOption) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {

        if (option == StopOption.ALL && nextUUID != null) {
            throw IllegalArgumentException("Stop block with All cannot have a next block")
        }
        val newNext = nextUUID?.toString()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_stop,
            fields = mapOf("STOP_OPTION" to listOf((option.s), null))
        ).toBlock(newNext, parent, context.topLevel)
    }
}

enum class StopOption(val s: String) {
    ALL("all"), OTHER("other scripts in sprite"), THIS("this script")
}

