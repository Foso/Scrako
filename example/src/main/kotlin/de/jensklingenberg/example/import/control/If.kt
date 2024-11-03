package control

import de.jensklingenberg.example.imports.extracted
import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class IfImport : ImportNode {
  override val opCode: String = "control_if"

  override fun visit(
    builder: StringBuilder,
    scratchProject: ScratchProject,
    target: Target,
    block: Block,
    myList: List<ImportNode>,
    id: String,
  ) {
    builder.append("ifThen(")

    val op = target.blocks.filter { it.value.parent == id && it.value.opcode.startsWith("operator_") }

    op.forEach { block ->
      extracted(block.key, target, myList, builder, scratchProject)
    }
    builder.append("){\n")
    target.blocks.filter { it.value.parent == id }.forEach { childBlock ->
      if(!childBlock.value.opcode.startsWith("operator_")){
        extracted(childBlock.key, target, myList, builder, scratchProject)
      }
    }
    builder.append("}\n")
  }
}
