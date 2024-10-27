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


val backdrop = Costume(
    name = "backdrop1",
    bitmapResolution = 1,
    dataFormat = "svg",
    assetId = "cd21514d0531fdffb22204e0ec5ed84a",
    md5ext = "cd21514d0531fdffb22204e0ec5ed84a.svg",
    rotationCenterX = 240.0,
    rotationCenterY = 180.0
)

val basketBall = Costume(
    name = "Basketball ",
    bitmapResolution = 1,
    dataFormat = "svg",
    assetId = "ae21eac3d1814aee1d37ae82ea287816",
    md5ext = "ae21eac3d1814aee1d37ae82ea287816.svg",
    rotationCenterX = 249.0,
    rotationCenterY = 186.0
)


fun defaultStage(variables: List<ScratchVariable> = emptyList()): Target {

    return Target(
        isStage = true,
        name = "Stage",
        variables = variables.associate {
            it.id.toString() to JsonArray(
                listOf(
                    JsonPrimitive(it.name),
                    JsonPrimitive("")
                )
            )
        },
        lists = emptyMap(),
        broadcasts = emptyMap(),
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