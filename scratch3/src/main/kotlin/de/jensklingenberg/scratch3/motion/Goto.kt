package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class Goto(private val block: ReporterBlock) : Node, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val uuid = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "motion_goto",
            inputs = mapOf(
                "TO" to setValue(block, uuid, context)
            )
        ).toBlock(nextUUID, parent)

        when (block) {
            is StringBlock -> {
                GotoMenu(block.value).visit(
                    visitors,
                    identifier,
                    uuid,
                    nextUUID, context,

                    )
            }

            else -> {
                block.visit(visitors, uuid, uuid, null, context)
            }
        }
    }
}

private class GotoMenu(val steps: String) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "motion_goto_menu",
            fields = mapOf("TO" to listOf(steps, null))
        ).toBlock(nextUUID, parent)

    }
}

fun CommonScriptBuilder.goTo(block: ReporterBlock) = addNode(Goto(block))
fun CommonScriptBuilder.goTo(value: String) = goTo(StringBlock(value))