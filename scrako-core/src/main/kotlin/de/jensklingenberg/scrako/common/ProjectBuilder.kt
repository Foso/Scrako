package de.jensklingenberg.scrako.common

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

class ProjectBuilder {
    val targets = mutableListOf<TargetBuilder>()
    var stage: TargetBuilder? = null
    private var variables = mutableSetOf<ScratchVariable>()


    fun addVariable(name: ScratchVariable) {
        variables.add(name)
    }

    fun build(): ScratchProject {

        val newStage = stage?.build(Context(variables)) ?: defaultStage(variables.toList())

        val ee = targets.map { it.build(Context(variables)) }

        //val target = targets.map { it.variables }
        return ScratchProject(
            targets = listOf(newStage) + ee
        )
    }
}


fun defaultStage(variables: List<ScratchVariable>) = Target(
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