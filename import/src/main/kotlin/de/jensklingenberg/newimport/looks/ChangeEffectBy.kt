package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class ChangeEffectBy : ImportNode {
    override val opCode: String = "looks_changeeffectby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeEffectBy(")
        handle(builder, target, myList, blockOr.inputs["CHANGE"]?.get(1))
        builder.append(",")
        builder.append(blockOr.fields["EFFECT"]?.get(0))
        builder.append(")\n")
    }
}