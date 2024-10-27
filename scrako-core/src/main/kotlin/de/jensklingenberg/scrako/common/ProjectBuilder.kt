package de.jensklingenberg.scrako.common

class ProjectBuilder {
    val targets = mutableListOf<Target>()
    var stage = defaultStage()
    var variables = mutableListOf<ScratchVariable>()

    fun build(): ScratchProject {
        val target = targets.map { it.variables }
        return ScratchProject(
            targets = listOf(stage) + targets.map { it }
        )
    }
}


fun defaultStage() = Target(
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