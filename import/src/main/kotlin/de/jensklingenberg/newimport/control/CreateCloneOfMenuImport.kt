package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class CreateCloneOfMenuImport : ImportNode {
    override val opCode: String = "control_create_clone_of_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("createCloneOfMenu(")
        builder.append(blockFullOr.fields["CLONE_OPTION"]?.get(0))
        builder.append(")\n")
    }

}