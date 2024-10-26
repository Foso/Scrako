package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createObjectContent
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

private class SensingOf(val block: ReporterBlock, val propertyName: String) : BooleanBlock, SensingBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_of,
            inputs = mapOf(
                "OBJECT" to createObjectContent(destinationUUID)
            ),
            fields = mapOf(
                "PROPERTY" to listOf(
                    propertyName, null
                )
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), destinationUUID, null, context.copy(topLevel = false))
    }
}

private class SensingOfMenu(val objectName: String) : BooleanBlock, SensingBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
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