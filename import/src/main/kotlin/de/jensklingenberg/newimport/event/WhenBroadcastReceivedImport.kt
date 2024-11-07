package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhenBroadcastReceivedImport : ImportNode {
    override val opCode: String = "event_whenbroadcastreceived"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        val broadcast = blockFullOr.fields["BROADCAST_OPTION"]?.get(0)
        builder.append("whenIReceiveBroadcast(${broadcast})\n")
    }
}