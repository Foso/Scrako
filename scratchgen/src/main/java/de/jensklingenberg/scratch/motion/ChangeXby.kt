package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class ChangeXby(val value: ReporterBlock) : Node, ReporterBlock {
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
                "DX" to setValue(value, operatorUUID)
            )
        ).toBlock(nextUUID, parent, context.topLevel)
        value.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

fun NodeBuilder.changeXby(value: ReporterBlock) = addChild(ChangeXby(value))
fun NodeBuilder.changeXby(value: Double) = addChild(ChangeXby(DoubleBlock(value)))
fun NodeBuilder.changeXby(value: Int) = addChild(ChangeXby(IntBlock(value)))
