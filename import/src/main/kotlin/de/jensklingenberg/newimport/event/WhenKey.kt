package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhenKey : ImportNode {
    override val opCode: String = "event_whenkeypressed"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenKeyPressed(Key.${blockFullOr.fields["KEY_OPTION"]?.get(0)})\n")
    }
}
