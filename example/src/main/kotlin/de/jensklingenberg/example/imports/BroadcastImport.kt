package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class BroadcastImport : ImportNode {
    override val opCode: String = "event_broadcast"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("broadcast(${blockOr.fields["BROADCAST_OPTION"]?.get(0)})\n")
    }

}