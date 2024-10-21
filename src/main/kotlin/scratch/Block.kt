package me.jens.scratch

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
    val comment: String? = null
)