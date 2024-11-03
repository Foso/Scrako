package de.jensklingenberg.newimport.event

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class WhenBroadcastReceived : ImportNode {
    override val opCode: String = "event_whenbroadcastreceived"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        val broadcast = blockOr.fields["BROADCAST_OPTION"]?.get(0)
        builder.append("whenIReceiveBroadcast(${broadcast})\n")
    }
}