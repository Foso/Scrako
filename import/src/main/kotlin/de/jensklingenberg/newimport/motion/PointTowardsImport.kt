package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class PointTowardsImport : ImportNode {
    override val opCode: String = "motion_pointtowards"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointTowards(")
        handle(builder, target, myList, block.inputs["TOWARDS"]?.get(1))
        builder.append(")\n")
    }
}