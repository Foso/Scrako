package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhileImport : ImportNode {
    override val opCode: String = "control_while"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("while(")
        handle(builder, target, myList, blockFullOr.inputs["CONDITION"]?.get(1))
        builder.append("){\n")
        handle(builder, target, myList, blockFullOr.inputs["SUBSTACK"]?.get(1))
        builder.append("}\n")
    }
}


//sensing_touchingobjectmenu

