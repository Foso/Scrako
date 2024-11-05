package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SwitchcostumetoImport : ImportNode {
    override val opCode: String = "looks_switchcostumeto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("switchCostume(")
        handle(builder, target, myList, blockOr.inputs["COSTUME"]?.get(1))
        builder.append(")\n")
    }
}


