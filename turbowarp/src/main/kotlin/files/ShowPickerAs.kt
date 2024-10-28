package files

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ObjectReporter
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

private class ShowPickerAs(val block0: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val block0Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = TurboOpCode.files_showPickerAs,
            inputs = mapOf(
                "as" to setValue(block0, block0Id)
            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, context)
    }
}

private class Menu_encoding(val encoding: String) : ObjectReporter {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {

        visitors[identifier.toString()] = BlockSpec(
            opcode = "files_menu_encoding",
            inputs = mapOf(

            ),
            fields = mapOf(
                "encoding" to listOf(encoding, null)
            )
        ).toBlock(nextUUID, parent)

    }
}

fun showPickerAs(encoding: String): BooleanBlock = ShowPickerAs(Menu_encoding(encoding))