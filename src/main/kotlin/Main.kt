package me.jens

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import scratch.BlockSpec
import scratch.Blocks
import scratch.OpCode
import scratch.createBlocks
import java.io.File

val source = "@.str = private unnamed_addr constant [13 x i8] c\"hello world\\0A\\00\", align 1\n" +
        "\n" +
        "declare i32 @printf(i8*, ...)\n" +
        "\n" +
        "define i32 @main() {\n" +
        "entry:\n" +
        "  %0 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i32 0, i32 0))\n" +
        "  ret i32 0\n" +
        "}"


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val blockSpecs = listOf(
        FlagClicked(),
        ControlIf(listOf(OperatorEquals("Hello", "World"), LooksSay("what"))),
        //ControlIf(listOf(OperatorEquals("Hello", "World")))

        /*
        BlockSpec(
            opcode = OpCode.LooksSay,
            inputs = mapOf(
                "MESSAGE" to createMessage("Hello, World!")
            ),
        )
         */
    )

    val blocks = createBlocks(blockSpecs)

    val input = File("/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/temp.txt").readText()

    val text = Json.encodeToString(Blocks.serializer(), blocks)

    val output = input.replace("REPLACE_BLOCKS", text.removeSurrounding("{", "}"))
    File("/Users/jens.klingenberg/Downloads/Archive(1)/project.json").writeText(output)
}

private fun FlagClicked() = BlockSpec(
    opcode = OpCode.Whenflagclicked,
    x = -444,
    y = 102
)

private fun OperatorEquals(operand1: String, operand2: String) = BlockSpec(
    opcode = OpCode.operator_equals,
    inputs = mapOf(
        "OPERAND1" to createOperand(operand1),
        "OPERAND2" to createOperand(operand2)
    ),
)

private fun ControlRepeat(times: Int, childs: List<BlockSpec>) = BlockSpec(
    opcode = OpCode.control_repeat,
    inputs = mapOf(
        "TIMES" to createTimes(times.toString()),
    ),
    childBlocks = childs
)

fun ControlIf(childs: List<BlockSpec>) = BlockSpec(
    opcode = OpCode.control_if,
    childBlocks = childs
)

fun LooksSay(message: String) = BlockSpec(
    opcode = OpCode.LooksSay,
    inputs = mapOf(
        "MESSAGE" to createMessage(message)
    )
)

fun LooksSayAnswer() = BlockSpec(
    opcode = OpCode.LooksSay,
    inputs = mapOf(
        "MESSAGE" to createAnswer()
    )
)


fun SensingAnswer() = BlockSpec(
    opcode = OpCode.sensing_answer,
)

fun SensingAskandWait(message: String) = BlockSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createMessage(message)
    )
)

fun LooksSayForSecs(message: String, duration: Int) = BlockSpec(
    opcode = OpCode.looks_sayforsecs,
    inputs = mapOf(
        "MESSAGE" to createMessage(message),
        "SECS" to createSecs(duration.toString())
    )
)


fun ControlWait(message: String) = BlockSpec(
    opcode = OpCode.ControlWait,
    inputs = mapOf(
        "DURATION" to createDuration(message)
    )
)

fun ControlForever(childs: List<BlockSpec>) = BlockSpec(
    opcode = OpCode.control_forever,
    childBlocks = childs
)


private fun createMessage(message: String) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(10),
                JsonPrimitive(message)
            )
        )
    )
)

private fun createAnswer() = JsonArray(
    listOf(
        JsonPrimitive(3),
        JsonPrimitive("answer"),
        JsonArray(
            listOf(
                JsonPrimitive(10),
                JsonPrimitive("Hello")
            )
        )
    )
)

private fun createDuration(message: String = "1") = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(5),
                JsonPrimitive(message)
            )
        )
    )
)

private fun createSecs(message: String = "1") = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(5),
                JsonPrimitive(message)
            )
        )
    )
)

private fun createTimes(message: String = "10") = JsonArray(
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


private fun createOperand(message: String) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(10),
                JsonPrimitive(message)
            )
        )
    )
)


class Builder() {
    fun getString(): String {
        return "Hello, World!"
    }
}