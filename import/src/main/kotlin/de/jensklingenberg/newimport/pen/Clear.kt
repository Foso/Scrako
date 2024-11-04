package de.jensklingenberg.newimport.pen

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class ClearImport : ImportNode {
  override val opCode: String = "pen_clear"

  override fun visit(
      builder: StringBuilder,
      project: ScratchProject,
      target: Target,
      blockOr: Block,
      myList: List<ImportNode>,
      blockId: String,
  ) {
    builder.append("eraseAll()\n")
  }
}



