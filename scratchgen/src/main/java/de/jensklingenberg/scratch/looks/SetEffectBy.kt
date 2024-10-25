package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class SetEffectBy(val block: ReporterBlock, val effectName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_seteffectto,
            inputs = mapOf(
                "VALUE" to setValue(block, operatorUUID)
            ),
            fields = mapOf(
                "EFFECT" to listOf(
                    effectName, null
                )
            )
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

fun NodeBuilder.setEffectTo(effectName: String, block: ReporterBlock) = addChild(SetEffectBy(block, effectName))