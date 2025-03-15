package de.jensklingenberg.newimport.event

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class BroadcastAndWaitImport : ImportNode {
    override val opCode: String = "event_broadcastandwait"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("broadcastAndWait(")
        handle(builder, target, myList, blockFull.inputs["BROADCAST_INPUT"]?.get(1))
        builder.append(")\n")
    }
}