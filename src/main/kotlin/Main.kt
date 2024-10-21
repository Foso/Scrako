package me.jens

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.createBlocks23
import me.jens.scratch.control.Repeat
import me.jens.scratch.control.Wait
import me.jens.scratch.createTarget
import me.jens.scratch.data.DeleteAllOf
import me.jens.scratch.data.ReplaceItemOfWith
import me.jens.scratch.event.ReceiveBroadcast
import me.jens.scratch.event.SendBroadcast
import me.jens.scratch.event.WhenKeyPress
import me.jens.scratch.looks.Say
import scratch.Broadcast
import scratch.Comment
import scratch.Costume
import scratch.ScratchList
import scratch.Meta
import scratch.ScratchProject
import scratch.Sound
import scratch.Sprite
import scratch.createStage
import scratch.looks.Hide
import scratch.writeProject
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

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

    val broadcast = Broadcast("hello")
    val myList = ScratchList("jens", listOf("1", "2", "3"))
    val comment = Comment(
        text = "Test"
    )

    val list1 = listOf(
        WhenKeyPress("space"),
        SendBroadcast(broadcast),
        Repeat(
            10,
            Say("hello"),
            Wait(1),
            Say("world"),
        )
    )

    val list2 = listOf(
        ReceiveBroadcast(broadcast),
        DeleteAllOf(myList),
        ReplaceItemOfWith(1, myList, "hello")
    )

    val list3 = listOf(
        ReceiveBroadcast(broadcast),
        Hide(),
    )

    val files = File("/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/").listFiles()
    val targetPath = "/Users/jens.klingenberg/Downloads"
    files?.forEach {
        Files.copy(it.toPath(), File("$targetPath/Archive(1)/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }


    val test = createBlocks23(listOf(list1, list2, list3))

    val sprite = Sprite(
        "sprite1", listOf(
            costume1,
            costum2
        ), listOf(
            sound1
        )
    )


    val target1 = createStage(listOf(myList))
    val targets = listOf(target1)+createTarget(test, sprite)

    val scratchProject = ScratchProject(
        targets = targets,
        meta = Meta(
            semver = "3.0.0",
            vm = "0.2.0",
            agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
        )
    )
    writeProject(scratchProject)

    val command = listOf("zip", "-r", "./test2.sb3", "./Archive(1)/")
    val processBuilder = ProcessBuilder(command)
    processBuilder.directory(File(targetPath))
    processBuilder.inheritIO() // This will redirect the output to the console

    try {
        val process = processBuilder.start()
        val exitCode = process.waitFor()
        println("Process exited with code: $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

val costume1 = Costume(
    name = "costume1",
    bitmapResolution = 1,
    dataFormat = "svg",
    assetId = "bcf454acf82e4504149f7ffe07081dbc",
    md5ext = "bcf454acf82e4504149f7ffe07081dbc.svg",
    rotationCenterX = 48,
    rotationCenterY = 50
)

val costum2 = Costume(
    name = "costume2",
    bitmapResolution = 1,
    dataFormat = "svg",
    assetId = "0fb9be3e8397c983338cb71dc84d0b25",
    md5ext = "0fb9be3e8397c983338cb71dc84d0b25.svg",
    rotationCenterX = 46,
    rotationCenterY = 53
)
val sound1 = Sound(
    name = "Meow",
    assetId = "83c36d806dc92327b9e7049a565c6bff",
    dataFormat = "wav",
    format = "",
    rate = 48000,
    sampleCount = 40681,
    md5ext = "83c36d806dc92327b9e7049a565c6bff.wav"
)


interface Event


fun SensingAskandWait(message: String) = BlockSpecSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createLiteralMessage(message)
    )
)


fun createLiteralMessage(message: String) = createMessage(1,10,message)

fun createMessage( first: Int, second: Int,message: String) = JsonArray(
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

