package de.jensklingenberg.newimport.pen

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder

public class SetPenColorToColor : ImportNode {
    override val opCode: String = "pen_setPenColorToColor"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setPenToColor(")
        handle(builder, target, myList, scratchProject, block.inputs["COLOR"]?.get(1))
        builder.append(")\n")
    }
}