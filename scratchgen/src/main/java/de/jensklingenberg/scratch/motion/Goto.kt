package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.StringBlock
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
                    is StringBlock -> {
                        JsonArray(listOf(JsonPrimitive(1), JsonPrimitive((uuid.toString()))))
                    }

                    else -> {
                        setValue(block, uuid)
                    }
                }
            )
        ).toBlock(nextUUID, parent)

        when (block) {
            is StringBlock -> {
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
        ).toBlock(nextUUID, parent)

    }
}

fun ScriptBuilder.goTo(block: ReporterBlock) = addChild(Goto(block))
fun ScriptBuilder.goTo(value: String) = goTo(StringBlock(value))