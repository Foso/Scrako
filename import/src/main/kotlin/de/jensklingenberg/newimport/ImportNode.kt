package de.jensklingenberg.newimport

import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

interface ImportNode {
    val opCode: String

    fun opCodeSupported(opCode: String): Boolean {
        return opCode == this.opCode
    }

    fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    )
}

