package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class GlideSecsToXYImport : ImportNode {
    override val opCode: String = "motion_glidesecstoxy"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("glideSecsToXY(")
        handle(builder, target, myList, blockOr.inputs["SECS"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockOr.inputs["X"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockOr.inputs["Y"]?.get(1))
        builder.append(")\n")
    }
}