package `operator`

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class AddImport : ImportNode {
  override val opCode: String = "operator_add"

  override fun visit(
    builder: StringBuilder,
    scratchProject: ScratchProject,
    target: Target,
    block: Block,
    myList: List<ImportNode>,
    id: String,
  ) {
    builder.append("add()\n")
  }
}
