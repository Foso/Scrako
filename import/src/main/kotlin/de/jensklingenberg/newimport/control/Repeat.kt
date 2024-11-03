package de.jensklingenberg.newimport.control

import de.jensklingenberg.example.imports.extracted
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

public class RepeatImport : ImportNode {
    override val opCode: String = "control_repeat"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("repeat(")

        handle(builder, target, myList, project, blockOr.inputs["TIMES"]?.get(1))

        builder.append("){\n")
        val substackId = blockOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
        val substackBlock = target.blocks[substackId]

        substackBlock?.let {
            extracted(substackId!!, target, myList, builder, project)
        }

        builder.append("}\n")

    }

}
