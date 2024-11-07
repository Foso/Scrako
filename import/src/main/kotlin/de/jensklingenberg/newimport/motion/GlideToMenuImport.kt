package de.jensklingenberg.newimport.motion

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class GlideToMenuImport : ImportNode {
    override val opCode: String = "motion_glideto_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append(blockFullOr.fields["TO"]?.get(0))
    }
}

//motion_setrotationstyle

class SetRotationStyleImport : ImportNode {
    override val opCode: String = "motion_setrotationstyle"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setRotationStyle(")
        builder.append(blockFullOr.fields["STYLE"]?.get(0))
        builder.append(")\n")
    }
}