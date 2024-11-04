package de.jensklingenberg.newimport.looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class SetSizeImport : ImportNode {
    override val opCode: String = "looks_setsizeto"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("setSize(")
        handle(builder, target, myList, project, blockOr.inputs["SIZE"]?.get(1))
        builder.append(")\n")

    }

}