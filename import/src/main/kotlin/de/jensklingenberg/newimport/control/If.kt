package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class IfImport : ImportNode {
  override val opCode: String = "control_if"

  override fun visit(
    builder: StringBuilder,
    project: ScratchProject,
    target: Target,
    blockOr: Block,
    myList: List<ImportNode>,
    blockId: String,
  ) {
    builder.append("ifThen(")

    val conditionBlockId = blockOr.inputs["CONDITION"]?.get(1)?.jsonPrimitive?.contentOrNull
    val conditionBlock = target.blocks[conditionBlockId]

    conditionBlock?.let {
      extracted(conditionBlockId!!, target, myList, builder, project)
    }
    builder.append("){\n")

    val substackId = blockOr.inputs["SUBSTACK"]?.get(1)?.jsonPrimitive?.contentOrNull
    var substackBlock = target.blocks[substackId]

    substackBlock?.let {
      extracted(substackId!!, target, myList, builder, project)
    }

    builder.append("}\n")
  }
}




