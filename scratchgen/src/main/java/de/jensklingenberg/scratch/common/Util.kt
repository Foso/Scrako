package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.motion.DoubleBlock
import de.jensklingenberg.scratch.motion.IntBlock
import de.jensklingenberg.scratch.operator.OperatorAnd
import de.jensklingenberg.scratch.operator.createNum
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

enum class ScratchType(val value: Int) {
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



fun createLiteralMessage(message: String) = createMessage(1, ScratchType.STRING.value, message)

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


fun createBlockRef(refId: String) = JsonArray(
    listOf(
        JsonPrimitive(3),
        JsonPrimitive(refId),
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.STRING.value),
                JsonPrimitive("Hello")
            )
        )
    )
)


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

fun createTimes(message: String = "10") = JsonArray(
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

fun setValue(
    reporterBlock: ReporterBlock,
    operatorUUID: UUID
) = when (reporterBlock) {
    is IntBlock -> {
        createNum(reporterBlock.value.toString())
    }

    is DoubleBlock -> {
        createNum(reporterBlock.value.toString())
    }

    is StringReporter -> {
        createLiteralMessage(reporterBlock.value)
    }

    is OperatorAnd -> {
        createCondition( operatorUUID.toString())
    }

    else -> {
        createBlockRef(operatorUUID.toString())
    }
}

fun createCondition(operatorId: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonPrimitive(operatorId)
    )
)