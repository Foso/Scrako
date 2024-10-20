package scratch

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

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
data class Blocks(
    val blocks: Map<String, Block>
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
    fun visit(visitors: MutableMap<String, Block>, layer: Int = 0, parent: String? = null, index: Int, next: String?)
}

fun CommonBlockSpec.toBlock(next: String?, parent: String?, topLevel: Boolean) = Block(
    opcode = opcode,
    next = next,
    parent = parent,
    inputs = inputs,
    fields = fields,
    shadow = shadow,
    topLevel = topLevel,
    x = x,
    y = y
)

fun createBlocks2(blockSpecs: List<Visitor>, layer: Int = 0, parent2: String? = null): Blocks {

    val blockMap = mutableMapOf<String, Block>()

    blockSpecs.forEachIndexed { index, blockSpec ->
        val topLevel = index == 0 && layer == 0

        val parent = when {
            topLevel -> null
            parent2 != null && index == 0 -> parent2
            else -> ("block" + (index - 1)) + layer
        }

        val next =
             if (index == blockSpecs.lastIndex) null else "block${index + 1}$layer"

        blockSpec.visit(blockMap, layer,parent, index, next)


    }

    return Blocks(blockMap)
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