package de.jensklingenberg.example.newimport

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.extracted
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

class SayImport : ImportNode {
    override val opCode: String = "looks_say"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("say(")
        handle(builder, target, myList, blockOr.inputs["MESSAGE"]?.get(1))
        builder.append(")\n")
    }
}

fun handle(
    builder: StringBuilder,
    target: Target,
    myList: List<ImportNode>,
    jsonElement: JsonElement?
) {
    when (val id = jsonElement) {
        is JsonArray -> {
            val type = ScratchType.entries.find { it.value == id[0].jsonPrimitive.content.toInt() }

            if (type == ScratchType.STRING) {
                builder.append("\"" + id[1].jsonPrimitive.content + "\"")
            } else {
                builder.append(id[1].jsonPrimitive.content.removePrefix("null"))
            }
        }

        is JsonPrimitive -> {
            val timeBlockId = id.toString().removeSurrounding("\"")
            val timeBlock = target.blocks[timeBlockId]

            timeBlock?.let {
                extracted(timeBlockId, target, myList, builder)
            }
        }

        else -> {
            println("NOT IMPLEMENTED")
        }
    }
}
