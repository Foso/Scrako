package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DistanceToMenuImport : ImportNode {
    override val opCode: String = "sensing_distancetomenu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("distanceToMenu(")
        builder.append(blockFullOr.fields["DISTANCETOMENU"]?.get(0))
        builder.append(")\n")
    }
}