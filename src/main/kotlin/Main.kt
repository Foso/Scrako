package me.jens

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.ScratchProject
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.createStage
import de.jensklingenberg.scratch.model.Sound
import de.jensklingenberg.scratch.readList
import de.jensklingenberg.scratch.resFolder
import de.jensklingenberg.scratch.writeProject
import kotlinx.serialization.json.Json
import me.jens.targets.MyTarget
import me.jens.targets.createSprite2
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
val sprite1 = Sprite(
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
    val test = File("C:\\Users\\jensk\\IdeaProjects\\ScraKo\\docs\\project.json")

    val tt = Json { ignoreUnknownKeys = true }.decodeFromString<ScratchProject>(test.readText())
    val myList = ScratchList("jens2", readList("jens.txt"))
    val template =
        File("C:\\Users\\jensk\\IdeaProjects\\ScraKo\\scratchgen\\src\\main\\java\\de\\jensklingenberg\\scratch\\hey.txt").readText()
    tt.targets.forEach {
        it.blocks.forEach { (t, u) ->
            println(u.opcode)
            // if(u.opcode == "motion_movesteps"){

            u.inputs

            val newInputs = u.inputs.map { "\"${it.key}\" to setValue(block, operatorUUID) " }.joinToString("\n") { it }
            val newFields = u.fields.map { "\"${it.key}\" to listOf(${it.value.mapIndexed { index, s -> it }}) " }
                .joinToString("\n") { it }
            val name = u.opcode.substringAfter("_").capitalize()
            val effects = u.fields.map { "val ${it.key.lowercase()}: String" }.joinToString { it }
            val blocks = (0..<u.inputs.size).mapIndexed { _, i -> "val block${i} = ReporterBlock" }.joinToString { it }

            val sec = template.replace("REPLACE_INPUT", newInputs)
                .replace("INSERT_EFFECT", effects)
                .replace("INSERT_PARAMETER", blocks)
                .replace("REPLACE_NAME", name)
                .replace(
                    "REPLACE_OPCODE", u.opcode
                ).replace("REPLACE_FIELDS", newFields)
            File("C:\\Users\\jensk\\IdeaProjects\\ScraKo\\temp\\" + name).writeText(sec)
            //  }
        }
    }
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

    writeProject(scratchProject, resFolder, "C:\\Users\\jensk\\IdeaProjects\\ScraKo\\temp")
}

