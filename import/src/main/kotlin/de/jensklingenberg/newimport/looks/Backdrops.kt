package de.jensklingenberg.newimport.looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class BackdropsImport : ImportNode {
    override val opCode: String = "de.jensklingenberg.scratch3.looks.looks_backdrops"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("backdrops()\n")
    }
}

