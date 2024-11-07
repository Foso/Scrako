package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

class RepeatImport : ImportNode {
    override val opCode: String = "control_repeat"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("repeat(")

        handle(builder, target, myList, blockFullOr.inputs["TIMES"]?.get(1))

        builder.append("){\n")
        val substackId = blockFullOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        val substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder)
        }

        builder.append("}\n")

    }

}

