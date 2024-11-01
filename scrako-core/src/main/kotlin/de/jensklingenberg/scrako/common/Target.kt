package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Comment
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Sound
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID


internal fun createTarget(
    blocks: Map<String, Block>,
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
    direction: Double
): Target {
    val targe2 = Target(
        isStage = false,
        name = name,
        variables = variables.map {
            it.value.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.key),
                    JsonPrimitive(1)
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
        volume = 100,
        layerOrder = 1,
        visible = visible,
        x = x,
        y = y,
        size = size,
        direction = direction,
        draggable = false,
        rotationStyle = "all around"
    )
    return targe2
}

internal fun defaultStage(
    variables: MutableMap<String, UUID> = mutableMapOf(),
    broadcasts: Map<String, String>
): Target {

    return Target(
        isStage = true,
        name = "Stage",
        variables = variables.map {
            it.value.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.key),
                    JsonPrimitive(1),
                    //JsonPrimitive(true)
                )
            )
        }.toMap(),
        lists = emptyMap(),
        broadcasts = broadcasts,
        blocks = emptyMap(),
        comments = emptyMap(),
        currentCostume = 0,
        costumes = listOf(
            backdrop
        ),
        sounds = emptyList(),
        volume = 100,
        layerOrder = 0,
        visible = false,
        x = 0.0,
        y = 0.0,
        size = 100.0,
        direction = 90.0,
        tempo = 60,
        draggable = false,
        rotationStyle = "all around"
    )
}