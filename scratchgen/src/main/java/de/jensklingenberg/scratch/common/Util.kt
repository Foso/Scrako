package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.looks.ColorBlock
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.operator.And
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal enum class ScratchType(val value: Int) {
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
    LIST(13)
}

internal fun createLiteralMessage(message: String) = createMessage(1, ScratchType.STRING.value, message)

internal fun createMessage(first: Int, second: Int, message: String): JsonArray {
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


internal fun createBlockRef(refId: String, type: ScratchType = ScratchType.BLOCKREF) = JsonArray(
    listOf(
        JsonPrimitive(type.value),
        JsonPrimitive(refId),
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.STRING.value),
                JsonPrimitive("Hello")
            )
        )
    )
)

internal fun getScratchType(message: String = "1", scratchType: ScratchType) = JsonArray(
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

internal fun createTimes(message: String = "10") = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(6),
                JsonPrimitive(message)
            )
        )
    )
)

internal fun createVariableContent(content: ScratchVariable) = JsonArray(
    listOf(
        JsonPrimitive(3),
        JsonArray(
            listOf(
                JsonPrimitive(12),
                JsonPrimitive(content.name),
                JsonPrimitive(content.id.toString())
            )
        ),
        JsonPrimitive(null)
    )
)

internal fun createListContent(content: ScratchList) = JsonArray(
    listOf(
        JsonPrimitive(3),
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.LIST.value),
                JsonPrimitive(content.name),
                JsonPrimitive(content.id.toString())
            )
        )
    )
)

fun createNum(message: String) = createMessage(1, 10, message)

internal fun setValue(
    reporterBlock: ReporterBlock,
    operatorUUID: UUID
) = when (reporterBlock) {
    is IntBlock -> {
        createMessage(1, 4, reporterBlock.value.toString())
    }

    is ScratchVariable -> {
        createVariableContent(reporterBlock)
    }

    is ScratchList -> {
        createListContent(reporterBlock)
    }

    is ColorBlock -> {
        createMessage(1, ScratchType.COLOR.value, reporterBlock.value)
    }

    is DoubleBlock -> {
        createNum(reporterBlock.value.toString())
    }

    is StringBlock -> {
        createLiteralMessage(reporterBlock.value)
    }

    is And -> {
        createCondition(operatorUUID.toString())
    }

    else -> {
        createBlockRef(operatorUUID.toString())
    }
}

internal fun createCondition(operatorId: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonPrimitive(operatorId)
    )
)

val Random = StringBlock("_random_")
val MousePointer = StringBlock("_mouse_")