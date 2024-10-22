package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class DistanceTo(private val destination: ReporterBlock) : Node, ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_distanceto,
            inputs = mapOf(
                "DISTANCETOMENU" to JsonArray(
                    listOf(
                        JsonPrimitive(1),
                        JsonPrimitive(destinationUUID.toString())
                    )
                )
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
        destination.visit(visitors, identifier.toString(), destinationUUID, null, context)

    }
}