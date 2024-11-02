package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class ChangeEffectBy(val block0: ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_changeeffectby",
            inputs = mapOf(
                "CHANGE" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(
                "EFFECT" to listOf(effect, null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
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

fun CommonScriptBuilder.changeEffectBy(effectName: String, block: ReporterBlock) = addNode(ChangeEffectBy(block, effectName))
fun CommonScriptBuilder.changeEffectBy(effect: Effect, block: ReporterBlock) =
    addNode(ChangeEffectBy(block, effect.name.toLowerCase()))