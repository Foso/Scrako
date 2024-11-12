package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class SetSize(val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_setsizeto",
            inputs = mapOf(
                "SIZE" to setValue(block, operatorUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, operatorUUID, null, context)
    }
}

fun SpriteScriptBuilder.setSize(block: ReporterBlock) = addNode(SetSize(block))
fun SpriteScriptBuilder.setSize(block: Int): Unit = setSize(IntBlock(block))