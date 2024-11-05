package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

public class RoundImport : ImportNode {
  override val opCode: String = "operator_round"
  override fun visit(
      builder: StringBuilder,
      project: ScratchProject,
      target: de.jensklingenberg.scrako.model.Target,
      blockOr: Block,
      myList: List<ImportNode>,
      blockId: String
  ) {
    builder.append("round(")
      handle(builder, target, myList, project, blockOr.inputs["NUM"]?.get(1))
    builder.append(")")
  }

}