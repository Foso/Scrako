package de.jensklingenberg.scratch

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.Costume
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchProject
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.Sound
import de.jensklingenberg.scrako.common.Target
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.model.createTarget
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

class ProjectBuilder {
    val targets = mutableListOf<Target>()
    var stage = defaultStage()
    var variables = mutableListOf<ScratchVariable>()

    fun build(): ScratchProject {
        val target = targets.map { it.variables }
        return ScratchProject(
            targets = listOf(stage) +targets.map { it }
        )
    }
}

fun ProjectBuilder.addStage(target: Target) {
    if (target.name != "Stage") {
        throw IllegalStateException("You can only add a stage to a project")
    }
    stage = target
}

fun defaultStage() = Target(
    isStage = true,
    name = "Stage",
    variables = emptyMap(),
    lists = emptyMap(),
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
    sounds = emptyList(),
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

fun projectBuilder(ff: ProjectBuilder.() -> Unit): ProjectBuilder {
    val node = ProjectBuilder()
    ff.invoke(node)
    return node
}

fun ProjectBuilder.stageBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)
    val test = createBlocks23(targetBuilder.nodesbuilder.map { it.childs })
    targetBuilder.blocks = test
    addStage(targetBuilder.build())
    return targetBuilder
}

fun ProjectBuilder.targetBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)
    val test = createBlocks23(targetBuilder.nodesbuilder.map { it.childs })
    targetBuilder.blocks = test
    targets.add(targetBuilder.build())
    return targetBuilder
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
