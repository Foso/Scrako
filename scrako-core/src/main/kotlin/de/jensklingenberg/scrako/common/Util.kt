package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.TargetBuilder
import de.jensklingenberg.scrako.builder.addStage
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
    LIST(13),
    OBJECT(1)
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
    operatorUUID: UUID
) = when (reporterBlock) {
    is IntBlock -> {
        createMessage(1, 4, reporterBlock.value.toString())
    }

    is ObjectReporter -> {
        JsonArray(
            listOf(
                JsonPrimitive(ScratchType.OBJECT.value),
                JsonPrimitive(operatorUUID.toString()),
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
                        JsonPrimitive(reporterBlock.id.toString())
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
        createMessage(1, 10, reporterBlock.value.toString())
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
                JsonPrimitive(operatorUUID.toString()),
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

fun projectBuilder(ff: ProjectBuilder.() -> Unit): ProjectBuilder {
    val node = ProjectBuilder()
    ff.invoke(node)
    return node
}

fun ProjectBuilder.stageBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)

    addStage(targetBuilder)
    return targetBuilder
}

fun ProjectBuilder.targetBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)
    targets.add(targetBuilder)
    return targetBuilder
}

fun TargetBuilder.scriptBuilder(builder: ScriptBuilder.() -> Unit): ScriptBuilder {
    val scriptBuilder = ScriptBuilder()
    builder.invoke(scriptBuilder)
    scriptBuilders.add(scriptBuilder)
    return scriptBuilder
}


fun ProjectBuilder.getGlobalVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(element)
    return element
}