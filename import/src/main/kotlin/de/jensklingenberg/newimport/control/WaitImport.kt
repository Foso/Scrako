package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WaitImport : ImportNode {
    override val opCode: String = "control_wait"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("wait(")

        handle(builder, target, myList, blockFullOr.inputs["DURATION"]?.get(1))
        builder.append(")\n")
    }
}