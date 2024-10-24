package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class Goto(private val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_goto,
            inputs = mapOf(
                "TO" to when (block) {
                    is StringReporter -> {
                        JsonArray(listOf(JsonPrimitive(1), JsonPrimitive((uuid.toString()))))
                    }

                    else -> {
                        setValue(block, uuid)
                    }
                }
            )
        ).toBlock(nextUUID, parent, context.topLevel)

        when (block) {
            is StringReporter -> {
                GotoMenu(block.value).visit(
                    visitors,
                    identifier.toString(),
                    uuid,
                    nextUUID,
                    context
                )
            }

            else -> {
                block.visit(visitors, uuid.toString(), uuid, null, context)
            }
        }
    }
}

private class GotoMenu(val steps: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_goto_menu,
            fields = mapOf("TO" to listOf(steps, null))
        ).toBlock(nextUUID, parent, context.topLevel)

    }
}

fun NodeBuilder.goTo(block: ReporterBlock) = addChild(Goto(block))
fun NodeBuilder.goTo(value: String) = goTo(StringReporter(value))