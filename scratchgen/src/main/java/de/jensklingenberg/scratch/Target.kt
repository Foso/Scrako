package de.jensklingenberg.scratch

import de.jensklingenberg.scratch.common.Block
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
    val comments: Map<String, de.jensklingenberg.scratch.Comment>,
    val currentCostume: Int,
    val costumes: List<de.jensklingenberg.scratch.Costume>,
    val sounds: List<de.jensklingenberg.scratch.Sound>,
    val volume: Int,
    val layerOrder: Int,
    val visible: Boolean,
    val x: Int,
    val y: Int,
    val size: Int,
    val direction: Int,
    val draggable: Boolean,
    val rotationStyle: String,
    val tempo: Int? = null,
    val videoTransparency: Int? = null,
    val videoState: String? = null,
    val textToSpeechLanguage: String? = null
)

fun createTarget(blocks: Map<String, Block>, sprite: de.jensklingenberg.scratch.Sprite, comments: List<de.jensklingenberg.scratch.Comment> = emptyList(), lists: List<de.jensklingenberg.scratch.ScratchList>? = emptyList()): Target {
    val targe2 = Target(
        isStage = false,
        name = sprite.name,
        variables = emptyMap(),
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
        size = 100,
        direction = 90,
        draggable = false,
        rotationStyle = "all around"
    )
    return targe2
}