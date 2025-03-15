package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class AskAndWaitImport : ImportNode {
    override val opCode: String = "sensing_askandwait"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("ask(")
        handle(builder, target, myList, blockFullOr.inputs["QUESTION"]?.get(1))
        builder.append(")\n")
    }
}