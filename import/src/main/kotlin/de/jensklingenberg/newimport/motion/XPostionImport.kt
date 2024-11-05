package de.jensklingenberg.newimport.motion

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class XPostionImport : ImportNode {
    override val opCode: String = "motion_xposition"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("XPosition")
    }
}