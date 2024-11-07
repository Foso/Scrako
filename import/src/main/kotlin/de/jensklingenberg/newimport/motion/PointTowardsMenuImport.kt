package de.jensklingenberg.newimport.motion

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class PointTowardsMenuImport : ImportNode {
    override val opCode: String = "motion_pointtowards_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointTowardsMenu(")
        builder.append(block.fields["TOWARDS"]?.get(0))
        builder.append(")\n")
    }
}