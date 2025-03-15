package debugger

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Mutation

private class Breakpoint : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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

fun CommonScriptBuilder.breakpoint() = addNode(Breakpoint())