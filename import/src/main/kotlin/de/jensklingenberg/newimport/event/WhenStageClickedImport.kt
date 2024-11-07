package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhenStageClickedImport : ImportNode {
    override val opCode: String = "event_whenstageclicked"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenStageClicked()\n")
    }
}