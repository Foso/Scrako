package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class ChangeXby(val block: ReporterBlock) : ReporterBlock, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.motion_changexby,
            inputs = mapOf(
                "DX" to setValue(block, operatorUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, operatorUUID, null, context)
    }
}

fun ScriptBuilder.changeXby(value: ReporterBlock) = addNode(ChangeXby(value))
fun ScriptBuilder.changeXby(value: Double) = addNode(ChangeXby(DoubleBlock(value)))
fun ScriptBuilder.changeXby(value: Int) = addNode(ChangeXby(IntBlock(value)))
