package de.jensklingenberg.newimport

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class DefaultImporter : ImportNode {
    override val opCode: String
        get() = "default"

    override fun opCodeSupported(opCode: String): Boolean {
        return true
    }

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("default: ${blockOr.opcode}\n")
    }

}
