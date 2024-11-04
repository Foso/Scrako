package de.jensklingenberg.newimport.operator

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder

public class ModImport : ImportNode {
  override val opCode: String = "operator_mod"

  override fun visit(
      builder: StringBuilder,
      project: ScratchProject,
      target: Target,
      blockOr: Block,
      myList: List<ImportNode>,
      blockId: String,
  ) {
    builder.append("(")
      handle(builder, target, myList, project, blockOr.inputs["NUM1"]?.get(1))
    builder.append(" mod ")
      handle(builder, target, myList, project, blockOr.inputs["NUM2"]?.get(1))
    builder.append(")")
  }
}