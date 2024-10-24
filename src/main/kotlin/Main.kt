package me.jens

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.ScratchProject
import de.jensklingenberg.scratch.createStage
import de.jensklingenberg.scratch.readList
import de.jensklingenberg.scratch.resFolder
import de.jensklingenberg.scratch.writeProject

val source = "@.str = private unnamed_addr constant [13 x i8] c\"hello world\\0A\\00\", align 1\n" +
        "\n" +
        "declare i32 @printf(i8*, ...)\n" +
        "\n" +
        "define i32 @main() {\n" +
        "entry:\n" +
        "  %0 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i32 0, i32 0))\n" +
        "  ret i32 0\n" +
        "}"


fun main() {

    val myList = ScratchList("jens2", readList("jens.txt"))
    val stageTarget = createStage(listOf(myList), listOf())

    val sprite1 = MySprite(myList)

    val scratchProject = ScratchProject(
        targets = listOf(stageTarget, sprite1)
    )

    writeProject(scratchProject, resFolder, "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp")
}

