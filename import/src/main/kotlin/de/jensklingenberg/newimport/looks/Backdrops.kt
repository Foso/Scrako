package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class BackdropsImport : ImportNode {
  override val opCode: String = "looks_backdrops"

  override fun visit(
      builder: StringBuilder,
      scratchProject: ScratchProject,
      target: Target,
      block: Block,
      myList: List<ImportNode>,
      id: String,
  ) {
    builder.append("backdrops()\n")
  }
}

public class ItemNumOfListImport : ImportNode {
    override val opCode: String = "data_itemnumoflist"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("itemNumOfList(")
        handle(builder, target, myList, scratchProject, block.inputs["ITEM"]?.get(1))
        builder.append(", ")
        builder.append(block.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}