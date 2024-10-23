package de.jensklingenberg.scratch.data


import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class DeleteAllOf(private val list: ScratchList) : Node, ListBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_deletealloflist,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent, context.topLevel)
    }
}

fun NodeBuilder.deleteAllOf(list: ScratchList) = addChild(DeleteAllOf(list))

interface VariablesBlock
interface ListBlock : VariablesBlock