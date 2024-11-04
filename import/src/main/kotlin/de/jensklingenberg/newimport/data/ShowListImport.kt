package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ShowListImport : ImportNode {
    override val opCode: String = "data_showlist"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("showList(")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}