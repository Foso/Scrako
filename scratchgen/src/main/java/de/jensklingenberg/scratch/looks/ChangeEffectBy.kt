package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ChangeEffectBy(val block: ReporterBlock, val effectName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_changeeffectby,
            inputs = mapOf(
                "CHANGE" to setValue(block, operatorUUID)
            ),
            fields = mapOf(
                "EFFECT" to listOf(
                    effectName, null
                )
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), operatorUUID, null, context)
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

fun NodeBuilder.changeEffectBy(effectName: String, block: ReporterBlock) = addChild(ChangeEffectBy(block, effectName))
fun NodeBuilder.changeEffectBy(effect: Effect, block: ReporterBlock) =
    addChild(ChangeEffectBy(block, effect.name.toLowerCase()))