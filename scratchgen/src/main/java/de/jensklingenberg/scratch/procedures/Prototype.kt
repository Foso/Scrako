package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.model.Mutation
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

class Prototype(val name: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.procedures_prototype,
            shadow = true,
            mutation = Mutation(tagName = "mutation",
                proccode = this.name,
                warp = "false",
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
    }
}

