package de.jensklingenberg.scratch3.control


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull

private class Stop(private val option: StopOption) : Node, CapBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "control_stop",
            fields = mapOf("STOP_OPTION" to listOf((option.s), null))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.stop(option: StopOption) = addNode(Stop(option))
enum class StopOption(val s: String) {
    ALL("all"), OTHER("other scripts in sprite"), THIS("this script")
}

