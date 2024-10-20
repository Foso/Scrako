package me.jens

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode
import me.jens.scratch.control.Repeat
import me.jens.scratch.event.FlagClicked
import me.jens.scratch.event.KeyPress
import me.jens.scratch.looks.LooksSayContent
import me.jens.scratch.looks.Say
import me.jens.scratch.control.Wait
import scratch.createBlocks23
import scratch.createTarget

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
        Wait(1),
        Say("Hello"),
        Say(LooksSayContent.Operators(OperatorAdd(1, 2))),
        Repeat(10, listOf(Wait(999))),
        )

    val test = createBlocks23(listOf(blockSpecs, listOf(KeyPress("any"))))
    createTarget(test)

}

interface Event


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

