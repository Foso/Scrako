package de.jensklingenberg.example.imports

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray

class BroadcastImport : ImportNode {
    override val opCode: String = "event_broadcast"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        val broadcastName = (blockOr.inputs["BROADCAST_INPUT"]?.get(1) as JsonArray)[1].toString()
        builder.append("broadcast(${broadcastName})\n")
    }

}