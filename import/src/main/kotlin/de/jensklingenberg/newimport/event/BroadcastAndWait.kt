package de.jensklingenberg.newimport.event

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class BroadcastAndWaitImport : ImportNode {
    override val opCode: String = "event_broadcastandwait"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("broadcastAndWait(")
        handle(builder, target, myList, scratchProject, block.inputs["BROADCAST_INPUT"]?.get(1))
        builder.append(")\n")
    }
}