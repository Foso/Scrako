package de.jensklingenberg.scratch.model

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.Comment
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scrako.common.ScratchVariable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import de.jensklingenberg.scrako.common.Target


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