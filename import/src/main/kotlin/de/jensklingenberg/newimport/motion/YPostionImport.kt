package de.jensklingenberg.newimport.motion

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class YPostionImport : ImportNode {
    override val opCode: String = "motion_yposition"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("YPosition")
    }
}