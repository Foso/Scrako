package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.StackBlock
import java.util.UUID

private class HideList(private val list: ScratchList) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_hideList,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.hideList(list: ScratchList) = addChild(HideList(list))
