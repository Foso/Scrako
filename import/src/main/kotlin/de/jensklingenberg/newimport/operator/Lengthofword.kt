package de.jensklingenberg.newimport.operator

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class LengthofWordImport : ImportNode {
    override val opCode: String = "operator_length"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("lengthof(")
        handle(builder,target,myList,project,blockOr.inputs["STRING"]?.get(1))
        builder.append(")")
    }
}
