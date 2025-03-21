import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class Deleteoflist(val block0: ReporterBlock, val list: String) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_deleteoflist",
            inputs = mapOf(
                "INDEX" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(
                "LIST" to listOf(list, null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun CommonScriptBuilder.deleteItemOfList(block0: ReporterBlock, list: String) = addNode(Deleteoflist(block0, list))
fun CommonScriptBuilder.deleteItemOfList(block0: ReporterBlock, list: ScratchList) =
    addNode(Deleteoflist(block0, list.name))
