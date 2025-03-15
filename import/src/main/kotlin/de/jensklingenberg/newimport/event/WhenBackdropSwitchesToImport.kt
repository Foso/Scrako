package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhenBackdropSwitchesToImport : ImportNode {
    override val opCode: String = "event_whenbackdropswitchesto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenBackdropSwitchesTo(${blockFullOr.fields["BACKDROP_OPTION"]?.get(0)})\n")
    }
}