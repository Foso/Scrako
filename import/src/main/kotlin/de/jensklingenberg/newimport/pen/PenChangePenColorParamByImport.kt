package de.jensklingenberg.newimport.pen

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class PenChangePenColorParamByImport : ImportNode {
    override val opCode: String = "pen_changePenColorParamBy"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changePenColorBy(")
        handle(builder, target, myList, blockFullOr.inputs["COLOR_PARAM"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockFullOr.inputs["VALUE"]?.get(1))

        builder.append(")\n")
    }
}