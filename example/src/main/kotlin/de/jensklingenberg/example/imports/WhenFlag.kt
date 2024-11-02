package de.jensklingenberg.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class WhenFlag : ImportNode {
    override val opCode: String = "event_whenflagclicked"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("whenFlagClicked()\n")
    }
}

