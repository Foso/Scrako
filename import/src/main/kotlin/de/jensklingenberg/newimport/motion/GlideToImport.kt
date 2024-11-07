package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class GlideToImport : ImportNode {
    override val opCode: String = "motion_glideto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("glideTo(")
        handle(builder, target, myList, blockFull.inputs["SECS"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockFull.inputs["TO"]?.get(1))
        builder.append(")\n")
    }
}


//motion_pointtowards

//motion_pointtowards_menu

