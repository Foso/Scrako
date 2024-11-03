package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class SetxImport : ImportNode {
    override val opCode: String = "motion_setx"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setX(")
        handle(builder, target, myList, scratchProject, block.inputs["X"]?.get(1))
        builder.append(")\n")
    }
}
