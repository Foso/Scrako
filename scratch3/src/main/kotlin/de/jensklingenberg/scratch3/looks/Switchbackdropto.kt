import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StageBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scratch3.looks.looks_backdrops
import java.util.UUID

private class Switchbackdropto(val block0: ReporterBlock) : Node, StageBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_switchbackdropto",
            inputs = mapOf("BACKDROP" to setValue(block0, block0Id, context))
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

enum class BackdropOptions(val s: String) {
    PREVIOUS("previous backdrop"),
    NEXT("next backdrop"),
    RANDOM("random backdrop")
}

fun CommonScriptBuilder.switchBackdropTo(block0: BackdropOptions) = addNode(Switchbackdropto(looks_backdrops(block0.s)))
fun CommonScriptBuilder.switchBackdropTo(block0: String) = addNode(Switchbackdropto(looks_backdrops(block0)))


//looks_switchbackdroptoandwait
