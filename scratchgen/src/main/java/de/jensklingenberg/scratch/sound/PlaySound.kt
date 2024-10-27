package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Sound
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PlaySound(val soundName: String) : Node {

    override fun visit(
        visitors: MutableMap<String, de.jensklingenberg.scratch.model.Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: de.jensklingenberg.scratch.common.Context
    ) {
        val soundMenuId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_play,
            inputs = mapOf("SOUND_MENU" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(soundMenuId.toString()))))
        ).toBlock(nextUUID, parent)
        SoundsMenu(soundName).visit(visitors, soundMenuId.toString(), soundMenuId, null, context)
    }
}

fun ScriptBuilder.playSound(s: String) = addChild(PlaySound(s))
fun ScriptBuilder.playSound(s: Sound) = addChild(PlaySound(s.name))


private class SoundsMenu(private val soundName: String) : Node {

    override fun visit(
        visitors: MutableMap<String, de.jensklingenberg.scratch.model.Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: de.jensklingenberg.scratch.common.Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_sounds_menu,
            fields = mapOf("SOUND_MENU" to listOf(soundName, null))
        ).toBlock(nextUUID, parent)
    }
}

