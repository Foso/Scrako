package de.jensklingenberg.newimport.operator

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder

public class JoinImport : ImportNode {
    override val opCode: String = "operator_join"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, project, blockOr.inputs["STRING1"]?.get(1))
        builder.append(" lt ")
        handle(builder, target, myList, project, blockOr.inputs["STRING2"]?.get(1))
        builder.append(")")
    }
}