package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class SetEffectTo(val block: ReporterBlock, val effectName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val block1Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.looks_seteffectto,
            inputs = mapOf(
                "VALUE" to setValue(block, block1Id, context)
            ),
            fields = mapOf(
                "EFFECT" to listOf(
                    effectName, null
                )
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, block1Id, null, context)
    }
}

fun ScriptBuilder.setEffectTo(effectName: String, block: ReporterBlock) = addNode(SetEffectTo(block, effectName))