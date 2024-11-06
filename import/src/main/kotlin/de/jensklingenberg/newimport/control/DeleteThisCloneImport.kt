package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class DeleteThisCloneImport : ImportNode {
    override val opCode: String = "control_delete_this_clone"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("deleteThisClone()\n")
    }

}

