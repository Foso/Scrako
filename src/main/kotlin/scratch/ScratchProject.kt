package scratch

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.common.Node
import java.io.File
import java.util.UUID

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor> = emptyList(),
    val extensions: List<String> = emptyList(),
    val meta: Meta
)

@Serializable
data class ScratchList(
    val name: String,
    val contents: List<String>
)

@Serializable
data class Target(
    val isStage: Boolean,
    val name: String,
    val variables: Map<String, List<String>>,
    val lists: Map<String, JsonArray>,
    val broadcasts: Map<String, String>,
    val blocks: Map<String, Block>,
    val comments: Map<String, Comment>,
    val currentCostume: Int,
    val costumes: List<Costume>,
    val sounds: List<Sound>,
    val volume: Int,
    val layerOrder: Int,
    val visible: Boolean,
    val x: Int,
    val y: Int,
    val size: Int,
    val direction: Int,
    val draggable: Boolean,
    val rotationStyle: String,
    val tempo: Int? = null,
    val videoTransparency: Int? = null,
    val videoState: String? = null,
    val textToSpeechLanguage: String? = null
)

class Sprite(val name: String, val costumes: List<Costume>, val sounds: List<Sound>)

class Broadcast(val name: String) {
    val id = UUID.randomUUID()
}

fun createTarget(blocks: Map<String, Block>, sprite: Sprite): List<Target> {
    val targe2 = Target(
        isStage = false,
        name = sprite.name,
        variables = emptyMap(),
        lists = emptyMap(),
        broadcasts = emptyMap(),
        blocks = blocks,
        comments = emptyMap(),
        currentCostume = 0,
        costumes = sprite.costumes,
        sounds = sprite.sounds,
        volume = 100,
        layerOrder = 1,
        visible = true,
        x = 0,
        y = 0,
        size = 100,
        direction = 90,
        draggable = false,
        rotationStyle = "all around"
    )




    return listOf( targe2)
}

class List2(val name: String, val contents: List<String>) {

    val id = UUID.randomUUID()
    fun getMap(): Map<String, JsonArray> {
        return mapOf(
            name to JsonArray(contents.map { JsonPrimitive(it) })
        )
    }
}

fun createStage(lists: List<List2>? = emptyList()) = Target(
    isStage = true,
    name = "Stage",
    variables = emptyMap(),
    lists = lists?.associate { it.id.toString() to JsonArray(listOf(JsonPrimitive(it.name), JsonArray(it.contents.map { JsonPrimitive(it) }))) } ?: emptyMap(),
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

fun writeProject(scratchProject: ScratchProject) {


    val text = Json.encodeToString(ScratchProject.serializer(), scratchProject)

    File("/Users/jens.klingenberg/Downloads/Archive(1)/project.json").writeText(text)
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


@Serializable
data class Block(
    val opcode: String,
    val next: String?,
    val parent: String?,
    @EncodeDefault val inputs: Map<String, JsonArray> = emptyMap(),
    @EncodeDefault val fields: Map<String, List<String?>> = emptyMap(),
    val shadow: Boolean,
    val topLevel: Boolean,
    val x: Int? = null,
    val y: Int? = null
)


interface CommonBlockSpec : Node {
    val opcode: String
    val inputs: Map<String, JsonArray>
    val fields: Map<String, List<String?>>
    val shadow: Boolean
    val x: Int?
    val y: Int?
}


fun createSubStack(message: String) = JsonArray(
    listOf(
        JsonPrimitive(2),
        JsonPrimitive(message)
    )
)

@Serializable
data class Comment(
    val blockId: String,
    val text: String,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val minimized: Boolean
)

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