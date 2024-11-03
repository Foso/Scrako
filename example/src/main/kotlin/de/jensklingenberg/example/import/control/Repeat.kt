package control

import de.jensklingenberg.example.imports.extracted
import de.jensklingenberg.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

public class RepeatImport : ImportNode {
    override val opCode: String = "control_repeat"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("repeat(")
        handle(block.inputs["TIMES"]?.jsonArray)
        val firstTimesId = (block.inputs["TIMES"]?.get(0) as JsonPrimitive).contentOrNull

        when (firstTimesId) {
            "3" -> {

                val firstTimesId = (block.inputs["TIMES"]?.get(1) as JsonPrimitive)
                val blockRefType = firstTimesId.jsonPrimitive.contentOrNull

                when (blockRefType) {
                    "12" -> {
                        val timesId = (firstTimesId.jsonArray.get(1) as JsonPrimitive).contentOrNull
                        builder.append(timesId?.replace("null", ""))
                    }

                    else -> {
                        val timesId = (block.inputs["TIMES"]?.get(1) as JsonPrimitive).contentOrNull

                        val timesBlock = target.blocks[timesId]
                        timesBlock?.let {
                            extracted(timesId!!, target, myList, builder, scratchProject)
                        }
                    }
                }

                builder.append(") {\n")
            }

            "1" -> {
                val timesId = ((block.inputs["TIMES"]?.get(1) as JsonArray).jsonArray).get(1)
                builder.append(timesId)
            }
        }
        builder.append("){\n")

        val substackId = (block.inputs["SUBSTACK"]?.get(0) as JsonPrimitive).contentOrNull

        when (substackId) {
            "2" -> {
                target.blocks.filter { it.value.parent == id }.forEach { childBlock ->
                    if (!childBlock.value.opcode.startsWith("operator_")) {
                        extracted(childBlock.key, target, myList, builder, scratchProject)
                    }
                }
            }
        }

        builder.append("}")

    }

    private fun handle(jsonArray: JsonArray?) {
        val timesId = (jsonArray?.get(0) as JsonPrimitive).contentOrNull
        if (timesId == "3") {
            println("3")
        }
    }

}
