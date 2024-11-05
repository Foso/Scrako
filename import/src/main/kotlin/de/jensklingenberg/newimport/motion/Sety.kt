package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SetyImport : ImportNode {
    override val opCode: String = "motion_sety"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setY(")
        handle(builder, target, myList, block.inputs["Y"]?.get(1))
        builder.append(")\n")
    }
}
