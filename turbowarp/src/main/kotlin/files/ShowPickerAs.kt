package files

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ObjectReporter
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class ShowPickerAs(val block0: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "files_showPickerAs",
            inputs = mapOf(
                "as" to setValue(block0, block0Id, context)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

private class Menu_encoding(val encoding: String) : ObjectReporter {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "files_menu_encoding",
            fields = mapOf(
                "encoding" to listOf(encoding, null)
            )
        ).toBlock(nextUUID, parent)

    }
}

fun showPickerAs(encoding: String): BooleanBlock = ShowPickerAs(Menu_encoding(encoding))