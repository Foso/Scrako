package `operator`

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

public class EqualsImport : ImportNode {
  override val opCode: String = "operator_equals"

  override fun visit(
      builder: StringBuilder,
      scratchProject: ScratchProject,
      target: Target,
      block: Block,
      myList: List<ImportNode>,
      id: String,
  ) {

      var operand1 = ""

      when (val num1 = block.inputs["OPERAND1"]?.get(1)) {
          is JsonArray -> {
              val timeBlockId = num1[1].jsonPrimitive.contentOrNull?.removePrefix("null")
              operand1 = timeBlockId.orEmpty()
          }

          is JsonPrimitive -> {
              operand1 = num1.jsonPrimitive.content
          }

          else -> {

          }
      }


      var operand2 = ""

      when (val num2 = block.inputs["OPERAND2"]?.get(1)) {
          is JsonArray -> {
              val timeBlockId = num2[1].jsonPrimitive.contentOrNull?.removePrefix("null")
              operand2 = timeBlockId.orEmpty()
          }

          is JsonPrimitive -> {
              operand2 = num2.jsonPrimitive.content
          }

          else -> {

          }
      }

      builder.append("$operand1 eq $operand2")
  }
}
