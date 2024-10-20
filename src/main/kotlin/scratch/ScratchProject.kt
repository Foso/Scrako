package scratch

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.io.File

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor>,
    val extensions: List<String>,
    val meta: Meta
)

@Serializable
data class Target(
    val isStage: Boolean,
    val name: String,
    val variables: Map<String, List<String>>,
    val lists: Map<String, List<String>>,
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

fun createTarget(blocks: Map<String, Block>) {

    val target1 = Target(
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
    val targe2 = Target(
        isStage = false,
        name = "Sprite1",
        variables = emptyMap(),
        lists = emptyMap(),
        broadcasts = emptyMap(),
        blocks = blocks,
        comments = emptyMap(),
        currentCostume = 0,
        costumes = listOf(
            Costume(
                name = "costume1",
                bitmapResolution = 1,
                dataFormat = "svg",
                assetId = "bcf454acf82e4504149f7ffe07081dbc",
                md5ext = "bcf454acf82e4504149f7ffe07081dbc.svg",
                rotationCenterX = 48,
                rotationCenterY = 50
            ),
            Costume(
                name = "costume2",
                bitmapResolution = 1,
                dataFormat = "svg",
                assetId = "0fb9be3e8397c983338cb71dc84d0b25",
                md5ext = "0fb9be3e8397c983338cb71dc84d0b25.svg",
                rotationCenterX = 46,
                rotationCenterY = 53
            ),
        ),
        sounds = listOf(
            Sound(
                name = "Meow",
                assetId = "83c36d806dc92327b9e7049a565c6bff",
                dataFormat = "wav",
                format = "",
                rate = 48000,
                sampleCount = 40681,
                md5ext = "83c36d806dc92327b9e7049a565c6bff.wav"
            )
        ),
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

    val scratchProject = ScratchProject(
        targets = listOf(target1, targe2),
        monitors = emptyList(),
        extensions = emptyList(),
        meta = Meta(
            semver = "3.0.0",
            vm = "0.2.0",
            agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
        )
    )
    val input = File("/Users/jens.klingenberg/Code/2024/LLVMPoet/src/main/resources/temp.txt").readText()

    val text = Json.encodeToString(ScratchProject.serializer(), scratchProject)

    val output = input.replace("REPLACE_BLOCKS", text)
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

@Serializable
data class Input(
    val type: Int,
    val value: List<String>
)

interface HasChilds {
    val childBlocks: List<CommonBlockSpec>
}

interface CommonBlockSpec : HasChilds, Visitor {
    val opcode: String
    val inputs: Map<String, JsonArray>
    val fields: Map<String, List<String?>>
    val shadow: Boolean
    val x: Int?
    val y: Int?
}

interface Visitor {
    fun visit(
        visitors: MutableMap<String, Block>,
        layer: Int = 0,
        parent: String? = null,
        index: Int,
        next: Boolean,
        listIndex: Int
    )
}

fun createBlocks23(blockSpecs: List<List<Visitor>>): Map<String, Block> {
    val test: Map<String, Block> =
        blockSpecs.mapIndexed { index, visitors -> createBlocks2(visitors, index) }.flatMap { it.toList() }
            .toMap()

    return test
}


fun createBlocks2(blockSpecs: List<Visitor>, listInd: Int): Map<String, Block> {
    val blockMap = mutableMapOf<String, Block>()

    blockSpecs.forEachIndexed { index, blockSpec ->

        val parent = when {
            index == 0 -> null
            else -> "${listInd}_0_" + (index - 1).toString()
        }

        val next = index != blockSpecs.lastIndex

        blockSpec.visit(blockMap, 0, parent, index, next, listInd)

    }

    return blockMap
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