package de.jensklingenberg.scratch.extension.pen

import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import java.util.UUID

class Clear : Node {
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
            opcode = PenOpCode.pen_clear,
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer == 0)
    }
}