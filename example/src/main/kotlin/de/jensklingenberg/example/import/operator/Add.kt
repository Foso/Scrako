package `operator`

import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
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
    val operand1 = block.inputs["NUM1"]?.get(1)?.jsonArray?.get(1)?.jsonPrimitive?.content
    val operand2 = block.inputs["NUM2"]?.get(1)?.jsonArray?.get(1)?.jsonPrimitive?.content

    builder.append("$operand1 plus $operand2")
  }
}
