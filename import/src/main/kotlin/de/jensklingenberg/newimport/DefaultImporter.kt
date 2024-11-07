package de.jensklingenberg.newimport

import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DefaultImporter : ImportNode {
    override val opCode: String
        get() = "default"

    override fun opCodeSupported(opCode: String): Boolean {
        return true
    }

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("default: ${blockFullOr.opcode}\n")
        println("default: ${blockFullOr.opcode}\n")
    }

}
