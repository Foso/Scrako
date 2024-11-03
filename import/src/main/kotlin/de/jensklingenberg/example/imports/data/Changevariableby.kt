package de.jensklingenberg.example.imports.data

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class ChangevariablebyImport : ImportNode {
    override val opCode: String = "data_changevariableby"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        val variable = blockOr.fields["VARIABLE"]?.get(0)
        builder.append("changeVariableBy($variable,")
        handle(builder, target, myList, project, blockOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}
