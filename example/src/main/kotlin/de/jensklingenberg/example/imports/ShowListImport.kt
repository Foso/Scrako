package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class ShowListImport : ImportNode {
    override val opCode: String = "data_showlist"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("showList(PlayerIconID)\n")
    }
}