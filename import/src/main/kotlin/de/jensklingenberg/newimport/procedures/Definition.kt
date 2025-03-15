package de.jensklingenberg.newimport.procedures

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DefinitionImport : ImportNode {
    override val opCode: String = "procedures_definition"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("define(")
        handle(builder, target, myList, blockFullOr.inputs["custom_block"]?.get(1))
        builder.append(")\n")
    }

}
