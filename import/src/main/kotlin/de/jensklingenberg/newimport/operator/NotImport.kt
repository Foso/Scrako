package de.jensklingenberg.newimport.operator

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

public class NotImport : ImportNode {
  override val opCode: String = "operator_not"
  override fun visit(
    builder: StringBuilder,
    project: ScratchProject,
    target: de.jensklingenberg.scrako.model.Target,
    blockOr: Block,
    myList: List<ImportNode>,
    blockId: String
  ) {
    builder.append("!(")
    handle(builder, target, myList, project, blockOr.inputs["OPERAND"]?.get(1))
    builder.append(")")
  }

}



