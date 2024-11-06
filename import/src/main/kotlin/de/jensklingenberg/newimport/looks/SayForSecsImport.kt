package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SayForSecsImport : ImportNode {
    override val opCode: String = "looks_sayforsecs"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("sayForSecs(")
        handle(builder, target, myList, block.inputs["MESSAGE"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, block.inputs["SECS"]?.get(1))
        builder.append(")\n")
    }
}