package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class SetVariableImport : ImportNode {
    override val opCode: String = "data_setvariableto"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("setVariableTo(")
        builder.append("${blockOr.fields["VARIABLE"]?.get(0)},")
        handle(builder, target, myList, project, blockOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}



