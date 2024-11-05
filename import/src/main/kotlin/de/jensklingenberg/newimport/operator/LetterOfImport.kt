package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class LetterOfImport : ImportNode {
    override val opCode: String = "operator_letter_of"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("letterOf(")
        handle(builder, target, myList, blockOr.inputs["LETTER"]?.get(1))
        builder.append(", ")
        handle(builder, target, myList, blockOr.inputs["STRING"]?.get(1))
        builder.append(")")
    }
}