package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

//Unfishinished
private class DistanceTo(private val destination: ReporterBlock) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_distanceto,
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