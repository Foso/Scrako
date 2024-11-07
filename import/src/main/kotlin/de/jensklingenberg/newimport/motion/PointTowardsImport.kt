package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class PointTowardsImport : ImportNode {
    override val opCode: String = "motion_pointtowards"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointTowards(")
        handle(builder, target, myList, blockFull.inputs["TOWARDS"]?.get(1))
        builder.append(")\n")
    }
}