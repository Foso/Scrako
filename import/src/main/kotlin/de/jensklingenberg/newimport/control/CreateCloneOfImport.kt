package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class CreateCloneOfImport : ImportNode {
    override val opCode: String = "control_create_clone_of"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("createCloneOf(")
        handle(builder, target, myList, project, blockOr.inputs["CLONE_OPTION"]?.get(1))
        builder.append(")\n")
    }

}