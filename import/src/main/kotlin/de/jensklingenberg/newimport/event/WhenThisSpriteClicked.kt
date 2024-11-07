package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class WhenThisSpriteClicked : ImportNode {
    override val opCode: String = "event_whenthisspriteclicked"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenThisSpriteClicked()\n")
    }
}