package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ReplaceItemImport : ImportNode {
    override val opCode: String = "data_replaceitemoflist"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {

        builder.append("replaceItemOfListWith(")
        handle(builder, target, myList, project, blockOr.inputs["INDEX"]?.get(1))
        builder.append(", ")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(", ")
        handle(builder, target, myList, project, blockOr.inputs["ITEM"]?.get(1))
        builder.append(")\n")

    }
}

