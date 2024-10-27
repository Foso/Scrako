package me.jens

import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchProject
import de.jensklingenberg.scrako.common.Sound
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scrako.common.getGlobalVariable
import de.jensklingenberg.scrako.common.projectBuilder
import de.jensklingenberg.scratch.createStage
import de.jensklingenberg.scratch.readList
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
    val projectFile = File("/Users/jens.klingenberg/Code/2024/LLVMPoet/docs/project.json")

    val tt = Json { ignoreUnknownKeys = true }.decodeFromString<ScratchProject>(projectFile.readText())
    val myList =
        ScratchList(
            "jens2",
            readList("/Users/jens.klingenberg/Code/2024/LLVMPoet/example/src/main/resources/lists/jens.txt")
        )
    val template =
        File("/Users/jens.klingenberg/Code/2024/LLVMPoet/docs/hey.txt").readText()

    tt.targets.forEach {
        it.blocks.forEach { (t, u) ->
            println(u.opcode)
            // if(u.opcode == "motion_movesteps"){

            val newInputs =
                u.inputs.entries.mapIndexed { index, entry -> "\"${entry.key}\" to setValue(block${index}, block${index}Id) " }
                    .joinToString(",\n") { it }
            val newFields = u.fields.entries.mapIndexed { index, entry ->
                "\"${entry.key}\" to listOf(${entry.key.lowercase()},null)"
            }
                .joinToString("\n") { it }
            val name = u.opcode.substringAfter("_").capitalize()
            val effects = u.fields.map {
                "val ${it.key.lowercase()}: String"
            }.joinToString { it }
            val repl =
                List(u.inputs.entries.size) { index -> "val block${index}Id = UUID.randomUUID()" }.joinToString("\n") { it }
            val blocks =
                (0..<u.inputs.size).mapIndexed { _, i -> "val block${i} : ReporterBlock," }.joinToString("\n") { it }
            val wer = u.inputs.entries.mapIndexed { index, entry ->
                "block${index}.visit(visitors, identifier.toString(), block${index}Id, null)"
            }.joinToString("\n") { it }
            val sec = template.replace("REPLACE_INPUT", newInputs)
                .replace("REPLACE_BLOCKO", repl)
                .replace("HERE", wer)
                .replace("INSERT_EFFECT", effects)

                .replace("INSERT_PARAMETER", blocks)
                .replace("REPLACE_NAME", name)
                .replace(
                    "REPLACE_OPCODE", "OpCode." + u.opcode
                ).replace("REPLACE_FIELDS", newFields)
            File("/Users/jens.klingenberg/Code/2024/LLVMPoet/temp/" + name).writeText(sec)
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

    val proj = projectBuilder {

        val myVar = getGlobalVariable("myVar")

        MyTarget(myList)
        createSprite2()
    }


    writeProject(
        proj.build(),
        "/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/",
        "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp"
    )
}
