package de.jensklingenberg.newimport.pen

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class SetPenSizeImport : ImportNode {
    override val opCode: String = "pen_setPenSizeTo"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setPenSizeTo(")
        handle(builder, target, myList, project, blockOr.inputs["SIZE"]?.get(1))
        builder.append(")\n")
    }
}