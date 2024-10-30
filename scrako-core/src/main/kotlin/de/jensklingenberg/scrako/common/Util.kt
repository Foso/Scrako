package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.FunctionBuilder
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.TargetBuilder
import de.jensklingenberg.scrako.builder.addStage
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

enum class ScratchType(val value: Int) {
    OBJECT(1),
    BLOCKREF(3),
    NUMBER(4),
    POSITIVE_NUMBER(5),
    POSITIVE_INT(6),
    INT(7),
    ANGLE(8),
    COLOR(9),
    STRING(10),
    BROADCAST(11),
    VARIABLE(12),
    LIST(13),

}

fun createMessage(first: Int, second: Int, message: String): JsonArray {
    if (message.length > 330) {
        throw IllegalArgumentException("Message is too long")
    }
    return JsonArray(
        listOf(
            JsonPrimitive(first),
            JsonArray(
                listOf(
                    JsonPrimitive(second),
                    JsonPrimitive(message)
                )
            )
        )
    )
}


fun getScratchType(message: String = "1", scratchType: ScratchType) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(scratchType.value),
                JsonPrimitive(message)
            )
        )
    )
)

fun setValue(
    reporterBlock: ReporterBlock,
    operatorUUID: String,
    context: Context
) = when (reporterBlock) {
    is IntBlock -> {
        JsonArray(
            listOf(
                JsonPrimitive(1),
                JsonArray(
                    listOf(
                        JsonPrimitive(ScratchType.NUMBER.value),
                        JsonPrimitive(reporterBlock.value.toString())
                    )
                )
            )
        )
    }

    is ObjectReporter -> {
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.OBJECT.value),
                JsonPrimitive(operatorUUID),
            )
        )
    }

    is ScratchVariable -> {
        JsonArray(
            listOf(
                JsonPrimitive(3),
                JsonArray(
                    listOf(
                        JsonPrimitive(ScratchType.VARIABLE.value),
                        JsonPrimitive(reporterBlock.name),
                        JsonPrimitive(context.variableMap[reporterBlock.name].toString())
                    )
                ),
                JsonPrimitive(null)
            )
        )
    }

    is ScratchList -> {
        JsonArray(
            listOf(
                JsonPrimitive(3),
                JsonArray(
                    listOf(
                        JsonPrimitive(ScratchType.LIST.value),
                        JsonPrimitive(reporterBlock.name),
                        JsonPrimitive(reporterBlock.id.toString())
                    )
                )
            )
        )
    }

    is ColorBlock -> {
        createMessage(1, ScratchType.COLOR.value, reporterBlock.value)
    }

    is DoubleBlock -> {
        createMessage(1, ScratchType.STRING.value, reporterBlock.value.toString())
    }

    is StringBlock -> {
        createMessage(1, ScratchType.STRING.value, reporterBlock.value)
    }

    //is And -> {
    //     createCondition(operatorUUID.toString())
    //  }

    else -> {
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.BLOCKREF.value),
                JsonPrimitive(operatorUUID),
                JsonArray(
                    listOf(
                        JsonPrimitive(ScratchType.STRING.value),
                        JsonPrimitive("Hello")
                    )
                )
            )
        )
    }
}
