package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class MovestepsImport : ImportNode {
    override val opCode: String = "motion_movesteps"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("move(")
        handle(builder, target, myList, project, blockOr.inputs["STEPS"]?.get(1))
        builder.append(")\n")
    }
}
