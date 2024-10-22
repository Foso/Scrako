package scratch.procedures

import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Mutation
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
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
                argumentnames = "["
            )
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer == 0)
    }
}

