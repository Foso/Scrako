package debugger

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.model.Mutation
import de.jensklingenberg.scrako.common.Node

private class Breakpoint : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "procedures_call",
            inputs = emptyMap(),
            mutation = Mutation(
                tagName = "mutation",
                proccode = "breakpoint",
                warp = "false",
                argumentids = "[]",
            )
        ).toBlock(nextUUID, parent)

    }
}

fun ScriptBuilder.breakpoint() = addNode(Breakpoint())