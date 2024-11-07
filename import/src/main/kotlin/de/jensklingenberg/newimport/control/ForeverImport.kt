package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

class ForeverImport : ImportNode {
    override val opCode: String = "control_forever"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("forever {\n")
        val substackId = blockFullOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        var substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder)
        }
        builder.append("}")
    }
}