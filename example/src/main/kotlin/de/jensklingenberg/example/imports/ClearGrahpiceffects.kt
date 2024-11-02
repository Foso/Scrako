package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class ClearGrahpiceffects : ImportNode {
    override val opCode: String = "looks_cleargraphiceffects"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("cleargraphiceffects()\n")

    }

}

