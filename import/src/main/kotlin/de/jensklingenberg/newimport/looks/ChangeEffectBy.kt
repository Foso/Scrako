package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ChangeEffectBy : ImportNode {
    override val opCode: String = "looks_changeeffectby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeEffectBy(")
        handle(builder, target, myList, blockFullOr.inputs["CHANGE"]?.get(1))
        builder.append(",")
        builder.append(blockFullOr.fields["EFFECT"]?.get(0))
        builder.append(")\n")
    }
}


//looks_sayforsecs

