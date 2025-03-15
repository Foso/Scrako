package de.jensklingenberg.newimport.pen

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class PenMenuColorParamImport : ImportNode {
    override val opCode: String = "pen_menu_colorParam"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("penMenuColorParam(")
        builder.append(blockFullOr.fields["colorParam"]?.get(0))
        builder.append(")\n")
    }
}