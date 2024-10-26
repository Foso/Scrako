package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

//Unfishinished
private class DistanceTo(private val destination: ReporterBlock) : ReporterBlock {
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
        ).toBlock(nextUUID, parent)
        destination.visit(visitors, identifier.toString(), destinationUUID, null, context)

    }
}

fun NodeBuilder.distanceTo(destination: ReporterBlock) = addChild(DistanceTo(destination))