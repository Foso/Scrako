package de.jensklingenberg.scratch

import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.model.Costume
import de.jensklingenberg.scratch.model.Meta
import de.jensklingenberg.scratch.model.Monitor
import de.jensklingenberg.scratch.model.Sound
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor> = emptyList(),
    val extensions: List<String> = emptyList(),
    @EncodeDefault val meta: Meta = Meta(
        semver = "3.0.0",
        vm = "0.2.0",
        agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
    )
)

class TargetBuilder {
    val nodesbuilder = mutableListOf<ScriptBuilder>()
    var sprite: Sprite? = null
    var blocks: Map<String, Block> = mutableMapOf()

    fun build(): Target {
        return createTarget(
            this.blocks,
            this.sprite!!,
            emptyList(),
            this.nodesbuilder.map { it.lists }.flatten().toSet(),
            this.nodesbuilder.map { it.variables }.flatten().toSet()
        )
    }
}

fun TargetBuilder.addSprite(sprite: Sprite) {
    this.sprite = sprite
}

fun targetBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val node = TargetBuilder()
    ff.invoke(node)
    val test = createBlocks23(node.nodesbuilder.map { it.childs })
    node.blocks = test
    return node
}

fun TargetBuilder.scriptBuilder(builder: ScriptBuilder.() -> Unit): ScriptBuilder {
    val node = ScriptBuilder()
    builder.invoke(node)
    nodesbuilder.add(node)
    return node
}

class Sprite(
    val name: String,
    val costumes: List<Costume>,
    val sounds: List<Sound>,
    val size: Int = 100,
)

class Broadcast(val name: String) {
    val id: UUID = UUID.randomUUID()
}


class ScratchList(val name: String, val contents: List<String>) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}

fun ScriptBuilder.createList(name: String, contents: List<String>): ScratchList {
    val element = ScratchList(name, contents)
    lists.add(element)
    return element
}


fun readList(name: String): List<String> {
    val list = mutableListOf<String>()
    File(name).forEachLine {
        list.add(it)
    }
    return list
}

fun createStage(
    lists: List<ScratchList>? = emptyList(),
    variables: List<ScratchVariable>,
    sounds: List<Sound>
) = Target(
    isStage = true,
    name = "Stage",
    variables = variables.associate {
        it.id.toString() to JsonArray(
            listOf(
                JsonPrimitive(it.name),
                JsonPrimitive("")
            )
        )
    },
    lists = lists?.associate {
        it.id.toString() to JsonArray(
            listOf(
                JsonPrimitive(it.name),
                JsonArray(it.contents.map { JsonPrimitive(it) })
            )
        )
    } ?: emptyMap(),
    broadcasts = emptyMap(),
    blocks = emptyMap(),
    comments = emptyMap(),
    currentCostume = 0,
    costumes = listOf(
        Costume(
            name = "backdrop1",
            bitmapResolution = 1,
            dataFormat = "svg",
            assetId = "cd21514d0531fdffb22204e0ec5ed84a",
            md5ext = "cd21514d0531fdffb22204e0ec5ed84a.svg",
            rotationCenterX = 240.0,
            rotationCenterY = 180.0
        )
    ),
    sounds = sounds,
    volume = 100,
    layerOrder = 0,
    visible = false,
    x = 0,
    y = 0,
    size = 100,
    direction = 90,
    tempo = 60,
    draggable = false,
    rotationStyle = "all around"
)

fun copyFiles(inputPath: String, targetPath: String) {
    val soundFiles = File(inputPath + "sounds").listFiles()

    soundFiles?.forEach {
        Files.copy(it.toPath(), File("$targetPath/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    val spriteFiles = File(inputPath + "sprites").listFiles()

    spriteFiles?.forEach {
        Files.copy(it.toPath(), File("$targetPath/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
}

fun writeProject(scratchProject: ScratchProject, inputPath: String, targetPath: String) {

    copyFiles(inputPath, targetPath)

    val text = Json.encodeToString(ScratchProject.serializer(), scratchProject)

    File("$targetPath/project.json").writeText(text)

    val filesToZip = File(targetPath).listFiles()?.filter { !it.path.endsWith("sb3") }?.toList() ?: emptyList()
    val outputZipFile = File("$targetPath/test4.sb3")
    zipFiles(filesToZip, outputZipFile)

}

fun zipFiles(files: List<File>, outputZipFile: File) {
    ZipOutputStream(FileOutputStream(outputZipFile)).use { zipOut ->
        files.forEach { file ->
            FileInputStream(file).use { fis ->
                val zipEntry = ZipEntry(file.name)
                zipOut.putNextEntry(zipEntry)
                fis.copyTo(zipOut)
            }
        }
    }
}


fun createSubStack(message: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonPrimitive(message)
    )
)
