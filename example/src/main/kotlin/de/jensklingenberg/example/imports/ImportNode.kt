package me.jens.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

interface ImportNode {
    val opCode: String
    fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    )
}

class ForeverImport : ImportNode {
    override val opCode: String = "control_forever"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        builder.append("forever {hhhhh\n")
        project.targets.forEach {

            it.blocks.filter { it.value.parent == t }.forEach { (id, block) ->
                myList.forEach { it.visit(builder, project, block, myList, id) }
            }
           // it.blocks.filter { it.value.parent == t } }
        }
        builder.append("}")
        project.targets.forEach {
            it.blocks.filter { it.key == blockOr.next }.forEach { (id, block) ->
                myList.forEach { it.visit(builder, project, block, myList, id) }
            }
        }
    }
}