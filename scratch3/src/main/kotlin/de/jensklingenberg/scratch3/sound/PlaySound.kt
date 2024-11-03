package de.jensklingenberg.scratch3.sound

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Sound
import de.jensklingenberg.scratch3.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PlaySound(val block: ReporterBlock) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val soundMenuId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sound_play,
            inputs = mapOf("SOUND_MENU" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(soundMenuId))))
        ).toBlock(nextUUID, parent)
       block.visit(visitors, soundMenuId, soundMenuId, null, context)
    }
}

internal class SoundsMenu(private val soundName: String) : ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = "sound_sounds_menu",
            fields = mapOf("SOUND_MENU" to listOf(soundName, null))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.playSound(s: String) = addNode(PlaySound(SoundsMenu(s)))
fun CommonScriptBuilder.playSound(s: Sound) = addNode(PlaySound(SoundsMenu(s.name)))