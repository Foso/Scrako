package de.jensklingenberg.scrako.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class Comment(
    val text: String,
    val x: Double = 0.0,
    val y: Double = 0.0,
    val width: Double = 50.0,
    val height: Double = 50.0,
    val minimized: Boolean = true
) {
    private var blockId: String = ""

    @Transient
    val id = UUID.randomUUID().toString()

    fun addBlock(id: String) {
        blockId = id
    }
}

typealias Backdrop = Costume

@Serializable
data class Costume(
    val name: String,
    val bitmapResolution: Int? = null,
    val dataFormat: String,
    val assetId: String,
    @Transient val md5ext: String = "",
    val rotationCenterX: Double,
    val rotationCenterY: Double
)

@Serializable
data class Sound(
    val name: String,
    val assetId: String,
    val dataFormat: String,
    @EncodeDefault val format: String? = null,
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
    @Transient val value: Double = 0.0,
    val width: Double,
    val height: Double,
    val x: Double,
    val y: Double,
    val visible: Boolean
)

@Serializable
data class Meta(
    val semver: String,
    val vm: String,
    val agent: String
)