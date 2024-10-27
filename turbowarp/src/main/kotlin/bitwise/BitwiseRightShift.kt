package bitwise

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import files.TurboOpCode
import java.util.UUID

private class BitwiseRightShift(
    val block0: ReporterBlock,
    val block1: ReporterBlock,
) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID()
        val block1Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = TurboOpCode.Bitwise_bitwiseRightShift,
            inputs = mapOf(
                "LEFT" to setValue(block0, block0Id),
                "RIGHT" to setValue(block1, block1Id)
            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, context)
        block1.visit(visitors, identifier.toString(), block1Id, null, context)
    }
}

fun bitwiseRightShift(block0: ReporterBlock, block1: ReporterBlock): ReporterBlock = BitwiseRightShift(block0, block1)