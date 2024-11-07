package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SetEffectToImport : ImportNode {
    override val opCode: String = "looks_seteffectto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setEffectTo(")
        handle(builder, target, myList, blockFull.inputs["VALUE"]?.get(1))
        builder.append(",")
        builder.append(blockFull.fields["EFFECT"]?.get(0))
        builder.append(")\n")
    }
}


//looks_thinkforsecs

