package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

//Unfishinished
private class DistanceTo(private val destination: ReporterBlock) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sensing_distanceto",
            inputs = mapOf(
                "DISTANCETOMENU" to JsonArray(
                    listOf(
                        JsonPrimitive(1),
                        JsonPrimitive(destinationUUID)
                    )
                )
            )
        ).toBlock(nextUUID, parent)
        destination.visit(visitors, identifier, destinationUUID, null, context)
    }
}

fun CommonScriptBuilder.distanceTo(destination: ReporterBlock) = addNode(DistanceTo(destination))