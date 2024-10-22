package me.jens

import scratch.Costume
import scratch.ScratchList
import scratch.ScratchProject
import scratch.Sound
import scratch.createStage
import scratch.writeProject
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
const val resFolder = "/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/"

fun readList(name: String): List<String> {
    val list = mutableListOf<String>()
    File("${resFolder}lists/$name").forEachLine {
        list.add(it)
    }
    return list
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val myList = ScratchList("jens2", readList("jens.txt"))
    val stageTarget = createStage(listOf(myList), listOf())

    val sprite1 = MySprite(myList)

    val targets = listOf(stageTarget, sprite1)

    val scratchProject = ScratchProject(
        targets = targets
    )
    writeProject(scratchProject)
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

