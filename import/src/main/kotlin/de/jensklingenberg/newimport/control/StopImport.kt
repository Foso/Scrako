package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class StopImport : ImportNode {
    override val opCode: String = "control_stop"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("stop(")
        builder.append("${blockFullOr.fields["STOP_OPTION"]?.get(0)?.uppercase()}")
        builder.append(")\n")
    }
}