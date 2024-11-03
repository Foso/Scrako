package event

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder
import kotlin.String
import kotlin.collections.List

public class WhenbroadcastreceivedImport : ImportNode {
  override val opCode: String = "event_whenbroadcastreceived"

  override fun visit(
      builder: StringBuilder,
      scratchProject: ScratchProject,
      target: Target,
      block: Block,
      myList: List<ImportNode>,
      id: String,
  ) {
    builder.append("whenbroadcastreceived()\n")
  }
}
