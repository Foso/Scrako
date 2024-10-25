package me.jens

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.ScratchProject
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.createStage
import de.jensklingenberg.scratch.model.Sound
import de.jensklingenberg.scratch.readList
import de.jensklingenberg.scratch.resFolder
import de.jensklingenberg.scratch.writeProject
import me.jens.targets.MyTarget
import me.jens.targets.createSprite2

val source = "@.str = private unnamed_addr constant [13 x i8] c\"hello world\\0A\\00\", align 1\n" +
        "\n" +
        "declare i32 @printf(i8*, ...)\n" +
        "\n" +
        "define i32 @main() {\n" +
        "entry:\n" +
        "  %0 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i32 0, i32 0))\n" +
        "  ret i32 0\n" +
        "}"
val sprite = Sprite(
    "Sprite1", listOf(
        costume1,
        costum2
    ), listOf(
        sound1,
        sound2
    )
)

val spriteArrow = Sprite("Arrow1", listOf(costume1), listOf())

fun main() {

    val myList = ScratchList("jens2", readList("jens.txt"))
    val stageTarget = createStage(
        listOf(myList), listOf(), listOf(
            Sound(
                name = "pop",
                assetId = "83a9787d4cb6f3b7632b4ddfebf74367",
                dataFormat = "wav",
                format = "",
                rate = 48000,
                sampleCount = 1123,
                md5ext = "83a9787d4cb6f3b7632b4ddfebf74367.wav"
            )
        )
    )



    val sprite1 = MyTarget(myList)
    val sprite2 = createSprite2()
    val scratchProject = ScratchProject(
        targets = listOf(stageTarget, sprite1, sprite2)
    )

    writeProject(scratchProject, resFolder, "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp")
}

