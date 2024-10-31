package debugger

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Mutation
import java.util.UUID

private class Warn(val block0: ReporterBlock) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "procedures_call",
            inputs = mapOf(
                "arg0" to setValue(block0, block0Id, context)
            ),
            mutation = Mutation(
                tagName = "mutation",
                proccode = "​​warn​​ %s",
                warp = "false",
                argumentids = "[\"arg0\"]",
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun ScriptBuilder.warn(block0: ReporterBlock) = addNode(Warn(block0))