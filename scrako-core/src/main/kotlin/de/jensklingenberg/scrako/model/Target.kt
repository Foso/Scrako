package de.jensklingenberg.scrako.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class Target(
    val isStage: Boolean,
    val name: String,
    val variables: Map<String, JsonArray>,
    val lists: Map<String, JsonArray>,
    val broadcasts: Map<String, String>,
    val blocks: Map<String, Block>,
    val comments: Map<String, Comment>,
    val currentCostume: Int,
    val costumes: List<Costume>,
    val sounds: List<Sound>,
    val volume: Int,
    val layerOrder: Int,
    val visible: Boolean? = null,
    val x: Double? = null,
    val y: Double? = null,
    val size: Double? = null,
    val direction: Double? = null,
    val draggable: Boolean? = null,
    val rotationStyle: String? = null,
    val tempo: Int? = null,
    val videoTransparency: Int? = null,
    val videoState: String? = null,
    val textToSpeechLanguage: String? = null
)