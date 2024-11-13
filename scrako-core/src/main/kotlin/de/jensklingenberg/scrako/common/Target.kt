package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Comment
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Sound
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID


internal fun createTarget(
    blocks: Map<String, BlockFull>,
    name: String,
    comments: List<Comment> = emptyList(),
    lists: Map<String, ScratchList>,
    variables: Map<String, UUID>,
    costumes: List<Costume>,
    broadcasts: Map<String, String>,
    sounds: List<Sound>,
    size: Double,
    x: Double,
    y: Double,
    visible: Boolean,
    direction: Double,
    isStage: Boolean,
    layerOrder: Int,
    rotationStyle: String,
    volume: Int
): Target {
    return Target(
        isStage = isStage,
        name = name,
        variables = variables.map {
            it.value.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.key),
                    JsonPrimitive(layerOrder)
                )
            )
        }.toMap(),
        lists = lists.map {
            it.value.id.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.key),
                    JsonArray(it.value.contents.map { JsonPrimitive(it.toString()) })
                )
            )
        }.toMap(),
        broadcasts = broadcasts,
        blocks = blocks,
        comments = comments.associateBy { it.id },
        currentCostume = 0,
        costumes = costumes,
        sounds = sounds,
        volume = volume,
        layerOrder = layerOrder,
        visible = visible,
        x = x,
        y = y,
        size = size,
        direction = direction,
        draggable = isStage,
        rotationStyle = rotationStyle
    )
}

internal fun defaultStage(
    variables: MutableMap<String, UUID> = mutableMapOf(),
    broadcasts: Map<String, String>,
    lists: Map<String, ScratchList>,
): Target {

    return createTarget(
        emptyMap(),
        "Stage",
        emptyList(),
        lists,
        variables,
        listOf(
            Costume(
                name = backdrop.name,
                bitmapResolution = backdrop.bitmapResolution,
                dataFormat = backdrop.dataFormat!!,
                assetId = backdrop.assetId!!,
                rotationCenterX = backdrop.rotationCenterX,
                rotationCenterY = backdrop.rotationCenterY
            )
        ),
        broadcasts,
        emptyList(),
        100.0,
        0.0,
        0.0,
        false,
        90.0,
        true,
        0,
        "all around",
        100
    )
}