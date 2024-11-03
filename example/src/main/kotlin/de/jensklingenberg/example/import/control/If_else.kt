package control

import de.jensklingenberg.example.imports.extracted
import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class If_elseImport : ImportNode {
  override val opCode: String = "control_if_else"

  override fun visit(
    builder: StringBuilder,
    scratchProject: ScratchProject,
    target: Target,
    block: Block,
    myList: List<ImportNode>,
    id: String,
  ) {
    builder.append("if_else()\n")
    target.blocks.filter { it.value.parent == id }.forEach { block ->
      extracted(block.key, target, myList, builder, scratchProject)
    }
   // extracted(id,target,myList,builder,scratchProject)
  }
}
