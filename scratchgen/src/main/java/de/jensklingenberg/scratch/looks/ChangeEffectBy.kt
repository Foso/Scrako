package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode.Companion.looks_changeeffectby
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

private class ChangeEffectBy(val block0 : ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        val block0Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = looks_changeeffectby,
            inputs = mapOf(
                "CHANGE" to setValue(block0, block0Id)
            ),
            fields = mapOf(
                "EFFECT" to listOf(effect,null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, )
    }
}

enum class Effect {
    COLOR,
    FISHEYE,
    WHIRL,
    PIXELATE,
    MOSAIC,
    BRIGHTNESS,
    GHOST
}

fun ScriptBuilder.changeEffectBy(effectName: String, block: ReporterBlock) = addChild(ChangeEffectBy(block, effectName))
fun ScriptBuilder.changeEffectBy(effect: Effect, block: ReporterBlock) =
    addChild(ChangeEffectBy(block, effect.name.toLowerCase()))