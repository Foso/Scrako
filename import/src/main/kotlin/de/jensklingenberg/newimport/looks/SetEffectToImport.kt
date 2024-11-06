package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SetEffectToImport : ImportNode {
    override val opCode: String = "looks_seteffectto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setEffectTo(")
        handle(builder, target, myList, block.inputs["VALUE"]?.get(1))
        builder.append(",")
        builder.append(block.fields["EFFECT"]?.get(0))
        builder.append(")\n")
    }
}


//looks_thinkforsecs

