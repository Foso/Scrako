package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ChangeXby(val block: ReporterBlock) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_changexby,
            inputs = mapOf(
                "DX" to setValue(block, operatorUUID)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

fun ScriptBuilder.changeXby(value: ReporterBlock) = addChild(ChangeXby(value))
fun ScriptBuilder.changeXby(value: Double) = addChild(ChangeXby(DoubleBlock(value)))
fun ScriptBuilder.changeXby(value: Int) = addChild(ChangeXby(IntBlock(value)))
