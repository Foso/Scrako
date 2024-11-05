package de.jensklingenberg.newimport.argument

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target


public class ArgStringNumber : ImportNode {
  override val opCode: String = "argument_reporter_string_number"

  override fun visit(
    builder: StringBuilder,
    project: ScratchProject,
    target: Target,
    blockOr: Block,
    myList: List<ImportNode>,
    blockId: String,
  ) {
    builder.append("\""+blockOr.fields["VALUE"]?.get(0)+"\"")

  }
}