package looks

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class SwitchbackdroptoImport : ImportNode {
  override val opCode: String = "looks_switchbackdropto"

  override fun visit(
    builder: StringBuilder,
    scratchProject: ScratchProject,
    target: Target,
    block: Block,
    myList: List<ImportNode>,
    id: String,
  ) {
    builder.append("switchbackdropto()\n")
  }
}
