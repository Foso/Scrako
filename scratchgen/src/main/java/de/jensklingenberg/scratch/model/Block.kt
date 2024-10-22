package de.jensklingenberg.scratch.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

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
    val y: Int? = null,
    val comment: String? = null,
    val mutation: Mutation? = null
)

@Serializable
data class Mutation(
    val tagName: String,
    @EncodeDefault val children: List<String> = emptyList(),
    val proccode: String,
    @EncodeDefault val argumentids: String = "[]",
    @EncodeDefault val argumentnames: String = "[]",
    @EncodeDefault val argumentdefaults: String = "[]",
    @EncodeDefault val warp: String = "false",


    )