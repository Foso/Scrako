package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

/**
 * https://en.scratch-wiki.info/wiki/Set_Drag_Mode_()_(block)
 */
private class Setdragmode(val dragMode: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "sensing_setdragmode",
            fields = mapOf(
                "DRAG_MODE" to listOf(dragMode, null)
            )
        ).toBlock(nextUUID, parent)

    }
}

enum class DragMode(val value: String) {
    DRAG("draggable"),
    NOT_DRAG("not draggable")
}

fun CommonScriptBuilder.setDragMode(dragMode: DragMode) = addNode(Setdragmode(dragMode.value))