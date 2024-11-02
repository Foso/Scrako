package me.jens

import targets.Sprite2
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.builder.writeProject
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.looks.say
import kotlinx.serialization.json.Json
import me.jens.imports.BroadcastImport
import me.jens.imports.CallImport
import me.jens.imports.ClearGrahpiceffects
import me.jens.imports.ForeverImport
import me.jens.imports.ImportNode
import me.jens.imports.ReplaceItemImport
import me.jens.imports.SetVariableImport
import me.jens.imports.ShowListImport
import me.jens.imports.WhenFlag
import me.jens.imports.WhenKey
import me.jens.targets.MySprite1
import targets.Sprite2
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.zip.ZipInputStream

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    explicitNulls = false
}


fun main() {

    importer()


    val proj = projectBuilder {
        getGlobalVariable("myVar", true)
        val paint = createBroadcast("paint")
        val input = createBroadcast("input")
        MySprite1(paint, input)
        Sprite2(paint)
        stageBuilder {
            addCostumes(listOf(backdrop))

            scriptBuilder {
                whenFlagClicked()
                say("Hello!")
            }
        }
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


    val processBuilder2 = ProcessBuilder("open", "${outputPath}/$fileName")
    processBuilder2.inheritIO()
    val process2 = processBuilder2.start()
    process2.waitFor()
}


fun readList(name: String): List<String> {
    return File(name).readLines()
}

private fun importer(): ScratchList {
    var projectJson: String = ""

    val sb3Path = "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp/test4.sb3"

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
    val myList = mutableListOf<ImportNode>()
    val whenFlag = WhenFlag()
    myList.add(whenFlag)
    myList.add(ClearGrahpiceffects())
    myList.add(ReplaceItemImport())
    myList.add(ShowListImport())
    myList.add(SetVariableImport())
    myList.add(CallImport())
    myList.add(BroadcastImport())
    myList.add(WhenKey())
    myList.add(ForeverImport())

    val tt = json.decodeFromString<ScratchProject>(projectJson)
    val jensList =
        ScratchList(
            "jens2",
            readList("/Users/jens.klingenberg/Code/2024/LLVMPoet/example/src/main/resources/lists/jens.txt")
        )
    val template =
        File("/Users/jens.klingenberg/Code/2024/LLVMPoet/docs/hey.txt").readText()

    tt.targets.forEach { target ->

      val test =  target.costumes.joinToString("\n") {
          "val _${it.name.replace("-", "").replace("^","").replace(">","")} = Costume(\n" +
                    "    name = \"${it.name}\",\n" +
                    "    bitmapResolution = ${it.bitmapResolution},\n" +
                    "    dataFormat = \"${it.dataFormat}\",\n" +
                    "    assetId = \"${it.assetId}\",\n" +
                    "    md5ext = \"${it.md5ext}\",\n" +
                    "    rotationCenterX = ${it.rotationCenterX},\n" +
                    "    rotationCenterY = ${it.rotationCenterY}\n" +
                    ")"

        }

        File("/Users/jens.klingenberg/Code/2024/LLVMPoet/temp/Costumes${target.name}.kt").writeText(test)
        val builder = StringBuilder()

        target.blocks.filter { it.value.topLevel }.forEach { (id, block) ->
            myList.find { it.opCode == block.opcode }?.visit(builder, tt, block, myList, id)
        }

        target.blocks.forEach { (t, blockOr) ->

           // myList.find { it.opCode == blockOr.opcode }?.visit(builder, tt, blockOr, myList, t)
            val block = blockOr as? de.jensklingenberg.scrako.model.Block ?: return@forEach
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
            File("/Users/jens.klingenberg/Code/2024/LLVMPoet/wrapper/").mkdirs()
            File("/Users/jens.klingenberg/Code/2024/LLVMPoet/wrapper/" + name).writeText(sec)
            //  }
        }

        println(builder)
        println("-------------------------------------------------")
    }
    return jensList
}

