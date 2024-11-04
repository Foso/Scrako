package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class KeypressedImport : ImportNode {
  override val opCode: String = "sensing_keypressed"

  override fun visit(
      builder: StringBuilder,
      project: ScratchProject,
      target: Target,
      blockOr: Block,
      myList: List<ImportNode>,
      blockId: String,
  ) {
    builder.append("whenKeyPress(")
      handle(builder, target, myList, project, blockOr.inputs["KEY_OPTION"]?.get(1))
    builder.append(")\n")
  }
}
