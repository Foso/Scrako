package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class KeyoptionsImport : ImportNode {
  override val opCode: String = "sensing_keyoptions"

  override fun visit(
      builder: StringBuilder,
      scratchProject: ScratchProject,
      target: Target,
      blockOr: Block,
      myList: List<ImportNode>,
      id: String,
  ) {
    builder.append("\""+blockOr.fields["KEY_OPTION"]?.get(0)+"\"")
  }
}
