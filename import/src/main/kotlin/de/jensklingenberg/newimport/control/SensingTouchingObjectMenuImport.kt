package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SensingTouchingObjectMenuImport : ImportNode {
    override val opCode: String = "sensing_touchingobjectmenu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append(blockFullOr.fields["TOUCHINGOBJECTMENU"]?.get(0))
    }
}