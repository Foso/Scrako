package de.jensklingenberg.newimport.event

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class WhenBackdropSwitchesToImport : ImportNode {
    override val opCode: String = "event_whenbackdropswitchesto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenBackdropSwitchesTo(${blockOr.fields["BACKDROP_OPTION"]?.get(0)})\n")
    }
}