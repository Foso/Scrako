package de.jensklingenberg.scratch.control


import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class Stop(private val option: StopOption) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {

        if (option == StopOption.ALL && nextUUID != null) {
            throw IllegalArgumentException("Stop block with All cannot have a next block")
        }
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_stop,
            fields = mapOf("STOP_OPTION" to listOf((option.s), null))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.stop(option: StopOption) = addChild(Stop(option))
enum class StopOption(val s: String) {
    ALL("all"), OTHER("other scripts in sprite"), THIS("this script")
}

