package me.jens

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostume
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scrako.builder.writeProject
import kotlinx.serialization.json.Json
import me.jens.targets.MyTarget
import me.jens.targets.plus
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.zip.ZipInputStream

val sprite1 = Sprite(
    "Sprite1", listOf(
        sound1,
        sound2
    )
)

val backdropSprite = Sprite(
    "Stage", listOf(
    )
)

val basketSprite = Sprite(
    "Stage", listOf(
    )
)


val spriteArrow = Sprite("Arrow1", listOf())

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

val arr = (1..24).toMutableList()

fun get(y: Int, x: Int): Int {
    val width = 6

    return arr[y * width + x]

}

fun set(y: Int, x: Int, value: Int) {
    val width = 6

    arr[y * width + x] = value

}

fun main() {

    println(get(1,0))
    set(1,0,2)
    println(get(1,0))
    importer()
    val test = 2 + IntBlock(2)


    val proj = projectBuilder {
        //  getOrCreateGlobalList("myList")
        //getGlobalVariable("myVar")
        MyStage()
        MyTarget()
        //createSprite2()
    }


    val outputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/example/src/main/resources/"

    val fileName = "test4.sb3"
    writeProject(
        proj.build(),
        inputPath,
        outputPath,
        fileName
    )

    val processBuilder = ProcessBuilder("pkill", "-9", "TurboWarp")
    processBuilder.inheritIO()
    val process = processBuilder.start()
    process.waitFor()


    val processBuilder2 = ProcessBuilder("open", "${outputPath}/$fileName", "-a", "TurboWarp")
    processBuilder2.inheritIO()
    val process2 = processBuilder2.start()
    process2.waitFor()
}


fun readList(name: String): List<String> {
    val list = mutableListOf<String>()
    File(name).forEachLine {
        list.add(it)
    }
    return list
}

private fun importer(): ScratchList {
    var projectJson: String = ""

    val sb3Path = "/Users/jens.klingenberg/Code/2024/LLVMPoet/pj2.sb3"

    ZipInputStream(FileInputStream(sb3Path)).use { zis ->
        var entry = zis.nextEntry

        while (entry != null) {
            if (entry.name == "project.json") {
                val stringBuilder = StringBuilder()
                InputStreamReader(zis).use { isr ->
                    val buffer = CharArray(1024)
                    var length: Int
                    while (isr.read(buffer).also { length = it } != -1) {
                        stringBuilder.appendRange(buffer, 0, length)
                    }
                }
                projectJson = stringBuilder.toString()

            }

            //Zipentry to file
            try {
                zis.closeEntry()
                entry = zis.nextEntry
            } catch (e: Exception) {
                //e.printStackTrace()
                entry = null
            } finally {

            }

        }
    }

    val tt = json.decodeFromString<ScratchProject>(projectJson)
    val myList =
        ScratchList(
            "jens2",
            readList("/Users/jens.klingenberg/Code/2024/LLVMPoet/example/src/main/resources/lists/jens.txt")
        )
    val template =
        File("/Users/jens.klingenberg/Code/2024/LLVMPoet/docs/hey.txt").readText()

    tt.targets.forEach { target ->
        target.blocks.forEach { (t, block) ->
            if (block.opcode == "procedures_call") {
                println("ddd")
            }
            println(block.opcode)
            // if(block.opcode == "motion_movesteps"){

            val newInputs =
                block.inputs.entries.mapIndexed { index, entry -> "\"${entry.key}\" to setValue(block${index}, block${index}Id, context) " }
                    .joinToString(",\n") { it }
            val newFields = block.fields.entries.mapIndexed { index, entry ->
                "\"${entry.key}\" to listOf(${entry.key.lowercase()},null)"
            }
                .joinToString("\n") { it }
            val name = block.opcode.substringAfter("_").capitalize()
            val effects = block.fields.map {
                "val ${it.key.lowercase()}: String"
            }.joinToString { it }
            val repl =
                List(block.inputs.entries.size) { index -> "val block${index}Id = UUID.randomUUID().toString()" }.joinToString(
                    "\n"
                ) { it }
            val blocks =
                (0..<block.inputs.size).mapIndexed { _, i -> "val block${i} : ReporterBlock," }
                    .joinToString("\n") { it }
            val wer = block.inputs.entries.mapIndexed { index, entry ->
                "block${index}.visit(visitors, identifier, block${index}Id, null, context)"
            }.joinToString("\n") { it }
            val sec = template.replace("REPLACE_INPUT", newInputs)
                .replace("REPLACE_BLOCKO", repl)
                .replace("HERE", wer)
                .replace("INSERT_EFFECT", effects)

                .replace("INSERT_PARAMETER", blocks)
                .replace("REPLACE_NAME", name)
                .replace(
                    "REPLACE_OPCODE", "\"" + block.opcode + "\""
                ).replace("REPLACE_FIELDS", newFields)
            File("/Users/jens.klingenberg/Code/2024/LLVMPoet/temp/" + name).writeText(sec)
            //  }
        }
    }
    return myList
}

private fun ProjectBuilder.MyStage() {
    stageBuilder {
        addSprite(backdropSprite)
        addCostume(backdrop)
        scriptBuilder {
            whenFlagClicked()
            // goTo("100")
        }
    }
}
