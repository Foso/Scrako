package de.jensklingenberg.newimport.motion

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class PointInDirectionImport : ImportNode {
    override val opCode: String = "motion_pointindirection"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointInDirection(")
        handle(builder, target, myList, project, blockOr.inputs["DIRECTION"]?.get(1))
        builder.append(")\n")
    }
}
