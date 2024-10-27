package de.jensklingenberg.scrako.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

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


fun createTarget(
    blocks: Map<String, Block>,
    sprite: Sprite,
    comments: List<Comment> = emptyList(),
    lists: Set<ScratchList>? = emptySet(),
    variables: Set<ScratchVariable>
): Target {
    val targe2 = Target(
        isStage = false,
        name = sprite.name,
        variables = variables.associate {
            it.id.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.name),
                    JsonPrimitive("")
                )
            )
        },
        lists = lists?.associate {
            it.id.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.name),
                    JsonArray(it.contents.map { JsonPrimitive(it) })
                )
            )
        } ?: emptyMap(),
        broadcasts = emptyMap(),
        blocks = blocks,
        comments = comments.associateBy { it.id },
        currentCostume = 0,
        costumes = sprite.costumes,
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