package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class LetterOfImport : ImportNode {
    override val opCode: String = "operator_letter_of"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("letterOf(")
        handle(builder, target, myList, blockFullOr.inputs["LETTER"]?.get(1))
        builder.append(", ")
        handle(builder, target, myList, blockFullOr.inputs["STRING"]?.get(1))
        builder.append(")")
    }
}