package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.event.Key
import java.util.UUID

private class KeyIsPressed(val block: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_keypressed,
            inputs = mapOf(
                "KEY_OPTION" to setValue(block, destinationUUID)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), destinationUUID, null, context)
    }
}


fun keyIsPressed(key: Key): BooleanBlock = KeyIsPressed(KeyOptions(KeyReporter(key)))
fun keyIsPressed(block: ReporterBlock): BooleanBlock = KeyIsPressed(block)

private class KeyOptions(val reporter: KeyReporter) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_keyoptions,
            fields = mapOf(
                "KEY_OPTION" to listOf(reporter.key.value, null)
            )
        ).toBlock(nextUUID, parent)
        reporter.visit(visitors, identifier.toString(), destinationUUID, null, context)
    }
}