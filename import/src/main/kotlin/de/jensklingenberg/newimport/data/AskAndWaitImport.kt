package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class AskAndWaitImport : ImportNode {
    override val opCode: String = "sensing_askandwait"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("ask(")
        handle(builder, target, myList, project, blockOr.inputs["QUESTION"]?.get(1))
        builder.append(")\n")
    }
}