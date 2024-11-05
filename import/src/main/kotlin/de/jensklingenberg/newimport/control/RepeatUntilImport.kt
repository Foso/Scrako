package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

class RepeatUntilImport : ImportNode {
    override val opCode: String = "control_repeat_until"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("repeatUntil(")

        val conditionBlockId = blockOr.inputs["CONDITION"]?.get(1)?.jsonPrimitive?.contentOrNull
        val conditionBlock = target.blocks[conditionBlockId]

        conditionBlock?.let {
            extracted(conditionBlockId!!, target, myList, builder)
        }
        builder.append("){\n")

        val substackId = blockOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        var substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder)
        }

        builder.append("}\n")

    }

}