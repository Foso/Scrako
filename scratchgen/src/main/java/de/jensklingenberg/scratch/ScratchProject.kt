package de.jensklingenberg.scratch

import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.model.Costume
import de.jensklingenberg.scratch.model.Meta
import de.jensklingenberg.scratch.model.Monitor
import de.jensklingenberg.scratch.model.Sound
import de.jensklingenberg.scratch.model.Target
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

fun blockBuilder(ff: NodeBuilder.() -> Unit): NodeBuilder {
    val node = NodeBuilder()
    ff.invoke(node)
    return node
}

class Sprite(
    val name: String,
    val costumes: List<Costume>,
    val sounds: List<Sound>
)

class Broadcast(val name: String) {
    val id: UUID = UUID.randomUUID()
}


class ScratchList(val name: String, val contents: List<String>) : ReporterBlock {
    val id: UUID = UUID.randomUUID()
}

fun NodeBuilder.createList(name: String, contents: List<String>): ScratchList {
    val element = ScratchList(name, contents)
    lists.add(element)
    return element
}

const val resFolder = "/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/"

fun readList(name: String): List<String> {
    val list = mutableListOf<String>()
    File("${resFolder}lists/$name").forEachLine {
        list.add(it)
    }
    return list
}

fun createStage(
    lists: List<ScratchList>? = emptyList(),
    variables: List<ScratchVariable>
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
            rotationCenterX = 240,
            rotationCenterY = 180
        )
    ),
    sounds = listOf(
        Sound(
            name = "pop",
            assetId = "83a9787d4cb6f3b7632b4ddfebf74367",
            dataFormat = "wav",
            format = "",
            rate = 48000,
            sampleCount = 1123,
            md5ext = "83a9787d4cb6f3b7632b4ddfebf74367.wav"
        )
    ),
    volume = 100,
    layerOrder = 0,
    visible = false,
    x = 0,
    y = 0,
    size = 100,
    direction = 90,
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
