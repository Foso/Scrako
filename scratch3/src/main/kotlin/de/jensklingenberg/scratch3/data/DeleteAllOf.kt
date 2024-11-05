package de.jensklingenberg.scratch3.data


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ListBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode

private class DeleteAllOf(private val list: ScratchList) : Node, ListBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = OpCode.data_deletealloflist,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.deleteAllOf(list: ScratchList) = addNode(DeleteAllOf(list))

