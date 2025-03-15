package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class GotoxyImport : ImportNode {
    override val opCode: String = "motion_gotoxy"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("gotoxy(")
        handle(builder, target, myList, blockFull.inputs["X"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockFull.inputs["Y"]?.get(1))
        builder.append(")\n")
    }
}

//motion_glideto

//motion_glideto_menu

