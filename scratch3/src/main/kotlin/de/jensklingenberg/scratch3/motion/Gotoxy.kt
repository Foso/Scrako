import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class Gotoxy(
    val block0: ReporterBlock,
    val block1: ReporterBlock,
) : Node {
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
            opcode = "motion_gotoxy",
            inputs = mapOf(
                "X" to setValue(block0, block0Id, context),
                "Y" to setValue(block1, block1Id, context)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
        block1.visit(visitors, identifier, block1Id, null, context)
    }
}

fun CommonScriptBuilder.gotoxy(block0: ReporterBlock, block1: ReporterBlock) = addNode(Gotoxy(block0, block1))
fun CommonScriptBuilder.gotoxy(x: Int, y: Int) = addNode(Gotoxy(IntBlock(x), IntBlock(y)))