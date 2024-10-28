package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class Wait(private val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.ControlWait,
            inputs = mapOf(
                "DURATION" to setValue(block, uuid)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), uuid, null, context)
    }
}

fun ScriptBuilder.wait(seconds: Double) = addChild(Wait(DoubleBlock(seconds)))
fun ScriptBuilder.wait(seconds: Int) = addChild(Wait(IntBlock(seconds)))
fun ScriptBuilder.wait(block: ReporterBlock) = addChild(Wait(block))

