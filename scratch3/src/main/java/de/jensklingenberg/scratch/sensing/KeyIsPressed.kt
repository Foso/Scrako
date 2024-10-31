package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.event.Key
import java.util.UUID

private class KeyIsPressed(val block: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_keypressed,
            inputs = mapOf(
                "KEY_OPTION" to setValue(block, destinationUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, destinationUUID, null, context)
    }
}


fun keyIsPressed(key: Key): BooleanBlock = KeyIsPressed(KeyOptions(KeyReporter(key)))
fun keyIsPressed(block: ReporterBlock): BooleanBlock = KeyIsPressed(block)

private class KeyOptions(val reporter: KeyReporter) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_keyoptions,
            fields = mapOf(
                "KEY_OPTION" to listOf(reporter.key.value, null)
            )
        ).toBlock(nextUUID, parent)
        reporter.visit(visitors, identifier, destinationUUID, null, context)
    }
}