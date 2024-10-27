package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.createMessage
import java.util.UUID

private class ItemOfXList(private val index: Int, private val list: ScratchList) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_itemoflist,
            inputs = mapOf(
                "INDEX" to createMessage(1, 7, this.index.toString()),
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.itemOfXList(index: Int, list: ScratchList) =
    addChild(ItemOfXList(index, list))


