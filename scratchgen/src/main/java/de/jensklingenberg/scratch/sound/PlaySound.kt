package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.Sound
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PlaySound(val soundName: String) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        val soundMenuId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_play,
            inputs = mapOf("SOUND_MENU" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(soundMenuId.toString()))))
        ).toBlock(nextUUID, parent)
        SoundsMenu(soundName).visit(visitors, soundMenuId.toString(), soundMenuId, null, )
    }
}

fun ScriptBuilder.playSound(s: String) = addChild(PlaySound(s))
fun ScriptBuilder.playSound(s: Sound) = addChild(PlaySound(s.name))


internal class SoundsMenu(private val soundName: String) : ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_sounds_menu,
            fields = mapOf("SOUND_MENU" to listOf(soundName, null))
        ).toBlock(nextUUID, parent)
    }
}

