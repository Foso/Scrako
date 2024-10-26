package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

enum class GreaterThanOption(val option: String) {
    LOUDNESS("loudness"),
    TIMER("timer"),
}

private class WhenGreaterThan(private val option: GreaterThanOption, val value: ReporterBlock) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
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

fun NodeBuilder.whenGreaterThan(option: GreaterThanOption, value: ReporterBlock) =
    addChild(WhenGreaterThan(option, value))

fun NodeBuilder.whenGreaterThan(option: GreaterThanOption, value: Double) =
    addChild(WhenGreaterThan(option, DoubleBlock(value)))