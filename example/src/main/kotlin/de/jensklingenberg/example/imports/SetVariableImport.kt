package de.jensklingenberg.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class SetVariableImport : ImportNode {
    override val opCode: String = "data_setvariableto"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("setVariableTo(${blockOr.fields["VARIABLE"]?.get(0)}, ${blockOr.inputs["VALUE"]?.get(0)})\n")
    }
}