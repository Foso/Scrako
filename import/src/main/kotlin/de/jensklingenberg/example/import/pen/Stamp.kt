package de.jensklingenberg.example.import.pen

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class StampImport : ImportNode {
  override val opCode: String = "pen_stamp"

  override fun visit(
      builder: StringBuilder,
      scratchProject: ScratchProject,
      target: Target,
      block: Block,
      myList: List<ImportNode>,
      id: String,
  ) {
    builder.append("stamp()\n")
  }
}
