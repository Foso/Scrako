package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class GlideToImport : ImportNode {
    override val opCode: String = "motion_glideto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("glideTo(")
        handle(builder, target, myList, block.inputs["SECS"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, block.inputs["TO"]?.get(1))
        builder.append(")\n")
    }
}


//motion_pointtowards

class PointTowardsImport : ImportNode {
    override val opCode: String = "motion_pointtowards"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointTowards(")
        handle(builder, target, myList, block.inputs["TOWARDS"]?.get(1))
        builder.append(")\n")
    }
}

//motion_pointtowards_menu

class PointTowardsMenuImport : ImportNode {
    override val opCode: String = "motion_pointtowards_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("pointTowardsMenu(")
        builder.append(block.fields["TOWARDS"]?.get(0))
        builder.append(")\n")
    }
}