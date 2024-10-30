package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class SensingOf(val block: ReporterBlock, val propertyName: String) : BooleanBlock, SensingBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_of,
            inputs = mapOf(
                "OBJECT" to JsonArray(
                    listOf(
                        JsonPrimitive(ScratchType.OBJECT.value),
                        JsonPrimitive(destinationUUID),
                    )
                )
            ),
            fields = mapOf(
                "PROPERTY" to listOf(
                    propertyName, null
                )
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, destinationUUID, null, context)
    }
}

private class SensingOfMenu(val objectName: String) : BooleanBlock, SensingBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_of_object_menu,
            fields = mapOf(
                "OBJECT" to listOf(
                    objectName, null
                )
            )
        ).toBlock(nextUUID, parent)
    }
}

enum class SensingOptions(val value: String) {
    x_position("x position"),
    y_position("y position"),
    direction("direction"),
    costume_number("costume #"),
    costume_name("costume name"),
    size("size"),
    volume("volume"),
    backdrop_name("backdrop name"),
    backdrop_number("backdrop #"),
}

fun SensingOptions.of(sprite: Sprite): ReporterBlock = SensingOfMenu(sprite.name)

fun sensingOf(
    propertyName: SensingOptions,
    objectName: String
): ReporterBlock = SensingOf(SensingOfMenu(objectName), propertyName.value)

fun sensingOf(
    propertyName: SensingOptions,
    objectName: Sprite
): ReporterBlock = SensingOf(SensingOfMenu(objectName.name), propertyName.value)

fun sensingOf(
    propertyName: String,
    objectName: String
): ReporterBlock = SensingOf(SensingOfMenu(objectName), propertyName)