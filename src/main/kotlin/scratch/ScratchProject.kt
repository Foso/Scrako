package scratch

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.resFolder
import me.jens.scratch.Target
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.UUID

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor> = emptyList(),
    val extensions: List<String> = emptyList(),
    val meta: Meta = Meta(
        semver = "3.0.0",
        vm = "0.2.0",
        agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
    )
)

class Sprite(val name: String, val costumes: List<Costume>, val sounds: List<Sound>)

class Broadcast(val name: String) {
    val id: UUID = UUID.randomUUID()
}

class ScratchVariable(val name: String, val value: String) {
    val id: UUID = UUID.randomUUID()
}

open class ScratchList(open val name: String, val contents: List<String>) {
    val id: UUID = UUID.randomUUID()
}


fun createStage(lists: List<ScratchList>? = emptyList(), variables : List<ScratchVariable>) = Target(
    isStage = true,
    name = "Stage",
    variables = variables.associate {
        it.id.toString() to JsonArray(
            listOf(
                JsonPrimitive(it.name),
                JsonPrimitive(it.value)
            )
        )
    } ?: emptyMap(),
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

fun copyFiles(targetPath: String) {
    val soundFiles = File(resFolder + "sounds").listFiles()

    soundFiles?.forEach {
        Files.copy(it.toPath(), File("$targetPath/Archive(1)/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    val spriteFiles = File(resFolder + "sprites").listFiles()

    spriteFiles?.forEach {
        Files.copy(it.toPath(), File("$targetPath/Archive(1)/${it.name}").toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
}
fun writeProject(scratchProject: ScratchProject) {
    val targetPath = "/Users/jens.klingenberg/Downloads"
    copyFiles(targetPath)

    val text = Json.encodeToString(ScratchProject.serializer(), scratchProject)

    File("/Users/jens.klingenberg/Downloads/Archive(1)/project.json").writeText(text)

    val command = listOf("zip", "-r", "./test2.sb3", "./Archive(1)/")
    val processBuilder = ProcessBuilder(command)
    processBuilder.directory(File(targetPath))
    processBuilder.inheritIO() // This will redirect the output to the console

    try {
        val process = processBuilder.start()
        val exitCode = process.waitFor()
        println("Process exited with code: $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


private fun createCondition(operatorId: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonArray(
            listOf(
                JsonPrimitive(operatorId)
            )
        )
    )
)


fun createSubStack(message: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonPrimitive(message)
    )
)

@Serializable
data class Comment(
    val text: String,
    val x: Double = 0.0,
    val y: Double = 0.0,
    val width: Int = 50,
    val height: Int = 50,
    val minimized: Boolean = true
) {
    private var blockId: String = ""
    @Transient
    val id = UUID.randomUUID().toString()

    fun addBlock(id: String) {
        blockId = id
    }
}

@Serializable
data class Costume(
    val name: String,
    val bitmapResolution: Int? = null,
    val dataFormat: String,
    val assetId: String,
    val md5ext: String,
    val rotationCenterX: Int,
    val rotationCenterY: Int
)

@Serializable
data class Sound(
    val name: String,
    val assetId: String,
    val dataFormat: String,
    val format: String,
    val rate: Int,
    val sampleCount: Int,
    val md5ext: String
)

@Serializable
data class Monitor(
    val id: String,
    val mode: String,
    val opcode: String,
    val params: Map<String, String>,
    val spriteName: String?,
    val value: String,
    val width: Int,
    val height: Int,
    val x: Int,
    val y: Int,
    val visible: Boolean
)

@Serializable
data class Meta(
    val semver: String,
    val vm: String,
    val agent: String
)