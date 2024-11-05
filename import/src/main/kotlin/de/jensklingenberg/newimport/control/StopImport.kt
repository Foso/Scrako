package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class StopImport : ImportNode {
    override val opCode: String = "control_stop"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("stop(")
        builder.append("${blockOr.fields["STOP_OPTION"]?.get(0)?.uppercase()}")
        builder.append(")\n")
    }
}