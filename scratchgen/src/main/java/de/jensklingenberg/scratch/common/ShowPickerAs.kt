package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

private class ShowPickerAs(val block0 : ReporterBlock, ) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.files_showPickerAs,
            inputs = mapOf(
                "as" to setValue(block0, block0Id) 
            ),
            fields = mapOf(
                
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, context)
    }
}
interface ObjectReporter : ReporterBlock {

}
private class Menu_encoding( val encoding: String) : ObjectReporter {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.files_menu_encoding,
            inputs = mapOf(

            ),
            fields = mapOf(
                "encoding" to listOf(encoding,null)
            )
        ).toBlock(nextUUID, parent)

    }
}

fun showPickerAs(block0: String) : BooleanBlock = ShowPickerAs(Menu_encoding(block0))