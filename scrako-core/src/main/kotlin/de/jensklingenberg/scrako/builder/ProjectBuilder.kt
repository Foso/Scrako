package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchProject
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.defaultStage
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<TargetBuilder>()
    internal var stage: TargetBuilder? = null
    private var variables = mutableSetOf<ScratchVariable>()
    private var variableMap = mutableMapOf<String, ScratchVariable>()
    fun addVariable(name: ScratchVariable) {
        variables.add(name)
        variableMap[name.name] = ScratchVariable(name.name, UUID.randomUUID())
    }

    fun build(): ScratchProject {

        val newStage =
            stage?.build(Context( variableMap), true)?.copy(isStage = true, visible = false, layerOrder = 0)
                ?: defaultStage(
                    variables.toList()
                )

        val targets = targets.map { it.build(Context( variableMap), false) }

        return ScratchProject(
            targets = listOf(newStage) + targets
        )
    }
}

fun ProjectBuilder.addStage(target: TargetBuilder) {
    stage = target
}