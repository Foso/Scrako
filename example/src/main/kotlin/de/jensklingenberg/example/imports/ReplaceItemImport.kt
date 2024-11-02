package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class ReplaceItemImport : ImportNode {
    override val opCode: String = "data_replaceitemoflist"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("replaceItemOfListWith(getIndexOf(0, 0), jens2, PlayerIconID)\n")
    }
}

