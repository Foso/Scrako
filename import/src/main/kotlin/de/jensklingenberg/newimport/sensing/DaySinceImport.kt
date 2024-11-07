package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DaySinceImport : ImportNode {
    override val opCode: String = "sensing_dayssince2000"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("daysSince2000()")
    }
}