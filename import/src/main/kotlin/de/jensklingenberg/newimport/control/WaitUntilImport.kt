package de.jensklingenberg.newimport.control

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import java.lang.StringBuilder

public class WaitUntilImport : ImportNode {
  override val opCode: String = "control_wait_until"

  override fun visit(
      builder: StringBuilder,
      project: ScratchProject,
      target: Target,
      blockOr: Block,
      myList: List<ImportNode>,
      blockId: String,
  ) {
    builder.append("waitUntil(")

    val conditionBlockId = blockOr.inputs["CONDITION"]?.get(1)?.jsonPrimitive?.contentOrNull
    val conditionBlock = target.blocks[conditionBlockId]

    conditionBlock?.let {
        extracted(conditionBlockId!!, target, myList, builder, project)
    }
    builder.append(")")
  }
}