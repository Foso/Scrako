package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scratch3.event.Key
import java.util.UUID

private class KeyIsPressed(val block: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sensing_keypressed",
            inputs = mapOf(
                "KEY_OPTION" to setValue(block, destinationUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, destinationUUID, null, context)
    }
}

private class KeyOptions(val reporter: KeyReporter) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sensing_keyoptions",
            fields = mapOf(
                "KEY_OPTION" to listOf(reporter.key.value, null)
            )
        ).toBlock(nextUUID, parent)
        reporter.visit(visitors, identifier, destinationUUID, null, context)
    }
}

fun keyIsPressed(key: Key): BooleanBlock = KeyIsPressed(KeyOptions(KeyReporter(key)))
fun keyIsPressed(block: ReporterBlock): BooleanBlock = KeyIsPressed(block)
