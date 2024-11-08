package de.jensklingenberg.scrako.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class BlockFull(
    val opcode: String,
    val next: String?,
    val parent: String?,
    @EncodeDefault val inputs: Map<String, JsonArray> = emptyMap(),
    @EncodeDefault val fields: Map<String, List<String?>> = emptyMap(),
    val shadow: Boolean,
    val topLevel: Boolean,
    val x: Int? = null,
    val y: Int? = null,
    val comment: String? = null,
    val mutation: Mutation? = null
) : Block

@Serializable(with = ProjectSerializer::class)
sealed interface Block

@Serializable
class BlockList : Block


object ListItemSerializer : JsonTransformingSerializer<BlockList>(
    BlockList.serializer()
) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return buildJsonObject {

        }
    }
}

object ProjectSerializer : JsonContentPolymorphicSerializer<Block>(Block::class) {
    override fun selectDeserializer(element: JsonElement): KSerializer<out Block> {
        return when {
           element is JsonObject && element.jsonObject.contains("opcode") -> {
                BlockFull.serializer()
            }
            else -> {
                ListItemSerializer
            }
        }
    }


}

@Serializable
data class Mutation(
    val tagName: String,
    @EncodeDefault val children: List<String> = emptyList(),
    @EncodeDefault val proccode: String = "",
    @EncodeDefault val argumentids: String = "[]",
    @EncodeDefault val argumentnames: String = "[]",
    @EncodeDefault val argumentdefaults: String = "[]",
    @EncodeDefault val warp: String = "false",


    )