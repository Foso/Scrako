package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class CreateCloneOfMenuImport : ImportNode {
    override val opCode: String = "control_create_clone_of_menu"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("createCloneOfMenu(")
        builder.append(blockOr.fields["CLONE_OPTION"]?.get(0))
        builder.append(")\n")
    }

}