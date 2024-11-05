package de.jensklingenberg.newimport.pen

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SetPenColorToColor : ImportNode {
    override val opCode: String = "pen_setPenColorToColor"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setPenToColor(")
        handle(builder, target, myList, block.inputs["COLOR"]?.get(1))
        builder.append(")\n")
    }
}