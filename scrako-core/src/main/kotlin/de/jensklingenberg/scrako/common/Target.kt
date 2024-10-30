package de.jensklingenberg.scrako.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

@Serializable
data class Target(
    val isStage: Boolean,
    val name: String,
    val variables: Map<String, JsonArray>,
    val lists: Map<String, JsonArray>,
    val broadcasts: Map<String, String>,
    val blocks: Map<String, Block>,
    val comments: Map<String, Comment>,
    val currentCostume: Int,
    val costumes: List<Costume>,
    val sounds: List<Sound>,
    val volume: Int,
    val layerOrder: Int,
    val visible: Boolean? = null,
    val x: Int? = null,
    val y: Int? = null,
    val size: Int? = null,
    val direction: Int? = null,
    val draggable: Boolean? = null,
    val rotationStyle: String? = null,
    val tempo: Int? = null,
    val videoTransparency: Int? = null,
    val videoState: String? = null,
    val textToSpeechLanguage: String? = null
)


internal fun createTarget(
    blocks: Map<String, Block>,
    sprite: Sprite,
    name: String = sprite.name,
    comments: List<Comment> = emptyList(),
    lists: Map<String, ScratchList>,
    variables: Map<String, UUID>,
    costumes: List<Costume>,
    broadcasts: Map<String, String>
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
                    JsonArray(it.value.contents.map { JsonPrimitive(it.toInt()) })
                )
            )
        }.toMap(),
        broadcasts = broadcasts,
        blocks = blocks,
        comments = comments.associateBy { it.id },
        currentCostume = 0,
        costumes = costumes,
        sounds = sprite.sounds,
        volume = 100,
        layerOrder = 1,
        visible = true,
        x = 0,
        y = 0,
        size = sprite.size,
        direction = 90,
        draggable = false,
        rotationStyle = "all around"
    )
    return targe2
}

internal fun defaultStage(variables: MutableMap<String, UUID> = mutableMapOf(), broadcasts: Map<String, String>): Target {

    return Target(
        isStage = true,
        name = "Stage",
        variables = variables.map { (key, value) ->
            key to JsonArray(
                listOf(
                    JsonPrimitive(key),
                    JsonPrimitive(value.toString())
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
        x = 0,
        y = 0,
        size = 100,
        direction = 90,
        tempo = 60,
        draggable = false,
        rotationStyle = "all around"
    )
}