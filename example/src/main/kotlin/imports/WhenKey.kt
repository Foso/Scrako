package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class WhenKey : ImportNode {
    override val opCode: String = "event_whenkeypressed"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("whenKeyPressed(${blockOr.fields["KEY_OPTION"]?.get(0)})\n")
    }
}