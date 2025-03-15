package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

class IfImport : ImportNode {
    override val opCode: String = "control_if"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("ifThen(")

        val conditionBlockId = blockFullOr.inputs["CONDITION"]?.get(1)?.jsonPrimitive?.contentOrNull
        val conditionBlock = target.blocks[conditionBlockId]

        conditionBlock?.let {
            extracted(conditionBlockId!!, target, myList, builder)
        }
        builder.append("){\n")

        val substackId = blockFullOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        var substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder)
        }

        builder.append("}\n")
    }
}

