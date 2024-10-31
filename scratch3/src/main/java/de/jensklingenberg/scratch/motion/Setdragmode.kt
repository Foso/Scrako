package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

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
                "DRAG_MODE" to listOf(dragMode,null)
            )
        ).toBlock(nextUUID, parent)
        
    }
}

enum class DragMode(val value: String) {
    DRAG("draggable"),
    NOT_DRAG("not draggable")
}

fun ScriptBuilder.setDragMode(drag_mode: DragMode)  = addNode(Setdragmode(drag_mode.value))