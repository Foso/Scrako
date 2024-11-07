import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class Changesizeby(val block0 : ReporterBlock, ) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_changesizeby",
            inputs = mapOf(
                "CHANGE" to setValue(block0, block0Id, context) 
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun CommonScriptBuilder.changeSizeBy(block0 : ReporterBlock, ) = addNode(Changesizeby(block0))