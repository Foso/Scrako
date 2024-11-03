package de.jensklingenberg.example.imports

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ReplaceItemImport : ImportNode {
    override val opCode: String = "data_replaceitemoflist"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("replaceItemOfListWith(getIndexOf(0, 0), jens2, PlayerIconID)\n")
    }
}

