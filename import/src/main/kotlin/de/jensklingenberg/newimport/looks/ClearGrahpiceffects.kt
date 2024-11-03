package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ClearGrahpiceffects : ImportNode {
    override val opCode: String = "looks_cleargraphiceffects"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("cleargraphiceffects()\n")

    }

}

