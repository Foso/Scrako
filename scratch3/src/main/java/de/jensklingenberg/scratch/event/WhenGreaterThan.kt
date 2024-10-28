package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

enum class GreaterThanOption(val option: String) {
    LOUDNESS("loudness"),
    TIMER("timer"),
}

private class WhenGreaterThan(private val option: GreaterThanOption, val value: ReporterBlock) : Node, HatBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
        ) {
        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.event_whengreaterthan,
            inputs = mapOf("VALUE" to setValue(value, protoUUID)),
            fields = mapOf("WHENGREATERTHANMENU" to listOf(option.option, null))
        ).toBlock(nextUUID, parent)

        value.visit(visitors, identifier.toString(), protoUUID, null, context)
    }

}

fun ScriptBuilder.whenGreaterThan(option: GreaterThanOption, value: ReporterBlock) =
    addNode(WhenGreaterThan(option, value))

fun ScriptBuilder.whenGreaterThan(option: GreaterThanOption, value: Double) =
    addNode(WhenGreaterThan(option, DoubleBlock(value)))