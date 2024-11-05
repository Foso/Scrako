package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

class If_elseImport : ImportNode {
    override val opCode: String = "control_if_else"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("ifElse(")
        val conditionBlockId = blockOr.inputs["CONDITION"]?.get(1)?.jsonPrimitive?.contentOrNull
        val conditionBlock = target.blocks[conditionBlockId]

        conditionBlock?.let {
            extracted(conditionBlockId!!, target, myList, builder)
        }
        builder.append("), leftStack = {\n")
        val substackId = blockOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        var substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder)
        }

        builder.append("}, rightStack = {\n")
        val substackId2 = blockOr.inputs["SUBSTACK2"]?.get(1)?.jsonPrimitive?.contentOrNull
        var substackBlock2 = target.blocks[substackId2]
        substackBlock2?.let {
            extracted(substackId!!, target, myList, builder)
        }
        builder.append("}\n")

        // extracted(id,target,myList,builder,scratchProject)
    }
}
