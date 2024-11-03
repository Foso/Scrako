package de.jensklingenberg.example.imports

import de.jensklingenberg.imports.ImportNode
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
        builder.append("setVariableTo(${blockOr.fields["VARIABLE"]?.get(0)}, ${blockOr.inputs["VALUE"]?.get(0)})\n")
    }
}