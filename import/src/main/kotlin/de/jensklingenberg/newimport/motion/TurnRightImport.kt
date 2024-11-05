package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class TurnRightImport : ImportNode {
    override val opCode: String = "motion_turnright"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("turnRight(")
        handle(builder, target, myList, blockOr.inputs["DEGREES"]?.get(1))
        builder.append(")\n")
    }
}