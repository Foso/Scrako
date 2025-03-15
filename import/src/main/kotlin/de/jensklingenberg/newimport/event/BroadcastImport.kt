package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray

class BroadcastImport : ImportNode {
    override val opCode: String = "event_broadcast"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        val broadcastName = (blockFullOr.inputs["BROADCAST_INPUT"]?.get(1) as JsonArray)[1].toString()
        builder.append("broadcast(${broadcastName})\n")
    }

}