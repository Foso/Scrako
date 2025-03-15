package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class KeypressedImport : ImportNode {
    override val opCode: String = "sensing_keypressed"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("whenKeyPress(")
        handle(builder, target, myList, blockFullOr.inputs["KEY_OPTION"]?.get(1))
        builder.append(")\n")
    }
}
