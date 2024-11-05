package de.jensklingenberg.newimport.looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class GoToImport : ImportNode {
    override val opCode: String = "looks_gotofrontback"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("goTo")
      builder.append( blockOr.fields["FRONT_BACK"]?.get(0)?.capitalize())
        builder.append("()\n")
    }
}