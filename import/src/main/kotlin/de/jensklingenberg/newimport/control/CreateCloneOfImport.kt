package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class CreateCloneOfImport : ImportNode {
    override val opCode: String = "control_create_clone_of"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("createCloneOf(")
        handle(builder, target, myList, blockFullOr.inputs["CLONE_OPTION"]?.get(1))
        builder.append(")\n")
    }

}