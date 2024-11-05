package bitwise

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class BitwiseRightShift(
    val block0: ReporterBlock,
    val block1: ReporterBlock,
) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        val block1Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "Bitwise_bitwiseRightShift",
            inputs = mapOf(
                "LEFT" to setValue(block0, block0Id, context),
                "RIGHT" to setValue(block1, block1Id, context)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
        block1.visit(visitors, identifier, block1Id, null, context)
    }
}

fun bitwiseRightShift(block0: ReporterBlock, block1: ReporterBlock): ReporterBlock = BitwiseRightShift(block0, block1)