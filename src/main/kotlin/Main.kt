package me.jens

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.control.ControlStop
import me.jens.scratch.event.FlagClicked
import me.jens.scratch.looks.LooksSay
import me.jens.scratch.looks.LooksSayContent
import me.jens.scratch.motion.MoveSteps
import scratch.Blocks
import me.jens.scratch.OpCode
import me.jens.scratch.control.DeleteThisClone
import scratch.createBlocks2
import scratch.looks.Hide
import scratch.looks.Show
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
        LooksSay(LooksSayContent.Operators(OperatorAdd(4,3)),55),
        MoveSteps(13),
        Show(),
        ControlWait("1"),
        Hide(),
        DeleteThisClone(),
        ControlStop()


        /*
        BlockSpec(
            opcode = OpCode.LooksSay,
            inputs = mapOf(
                "MESSAGE" to createMessage("Hello, World!")
            ),
        )
         */
    )

    val blocks = createBlocks2(blockSpecs)

    val input = File("/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/temp.txt").readText()

    val text = Json.encodeToString(Blocks.serializer(), blocks)

    val output = input.replace("REPLACE_BLOCKS", text.removeSurrounding("{", "}"))
    File("/Users/jens.klingenberg/Downloads/Archive(1)/project.json").writeText(output)
}

interface Event

class OperatorEqualsSpec(private val operand1: String, private val operand2: String) : BlockSpecSpec(
    OpCode.operator_equals, inputs = mapOf(
        "OPERAND1" to createOperand(operand1),
        "OPERAND2" to createOperand(operand2)
    )
)

private fun ControlRepeat(times: Int, childs: List<BlockSpecSpec>) = BlockSpecSpec(
    opcode = OpCode.control_repeat,
    inputs = mapOf(
        "TIMES" to createTimes(times.toString()),
    ),
    childBlocks = childs
)

fun ControlIf(childs: List<BlockSpecSpec>) = BlockSpecSpec(
    opcode = OpCode.control_if,
    childBlocks = childs
)


fun SensingAskandWait(message: String) = BlockSpecSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createLiteralMessage(message)
    )
)




fun ControlWait(message: String) = BlockSpecSpec(
    opcode = OpCode.ControlWait,
    inputs = mapOf(
        "DURATION" to createDuration(message)
    )
)


fun createLiteralMessage(message: String) = JsonArray(
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


fun createBlockRef(refId: String) = JsonArray(
    listOf(
        JsonPrimitive(3),
        JsonPrimitive(refId),
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

fun createSecs(message: String = "1") = JsonArray(
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

