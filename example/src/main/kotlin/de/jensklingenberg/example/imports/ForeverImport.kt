package de.jensklingenberg.example.imports

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ForeverImport : ImportNode {
    override val opCode: String = "control_forever"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("forever {\n")
        target.blocks.filter { it.value.parent == blockId }.forEach { (id, block) ->
            myList.find { it.opCode == block.opcode }?.visit(builder, project, target, block, myList, id)
        }
        builder.append("}")
    }
}