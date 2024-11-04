package de.jensklingenberg.newimport.operator

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder

public class OrImport : ImportNode {
    override val opCode: String = "operator_or"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, project, blockOr.inputs["OPERAND1"]?.get(1))
        builder.append(" or ")
        handle(builder, target, myList, project, blockOr.inputs["OPERAND2"]?.get(1))
        builder.append(")")
    }
}