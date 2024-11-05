package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class TurnLeftImport : ImportNode {
    override val opCode: String = "motion_turnleft"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("turnLeft(")
        handle(builder, target, myList, blockOr.inputs["DEGREES"]?.get(1))
        builder.append(")\n")
    }
}