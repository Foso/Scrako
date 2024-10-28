package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchProject
import de.jensklingenberg.scrako.common.defaultStage
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<TargetBuilder>()
    internal var stage: TargetBuilder? = null
    private var globalVariableMap = mutableMapOf<String, UUID>()
    fun addVariable(name: String) {
        globalVariableMap[name] = UUID.randomUUID()
    }

    fun build(): ScratchProject {

        val newStage =
            stage?.build(Context(globalVariableMap), true)?.copy(isStage = true, visible = false, layerOrder = 0)
                ?: defaultStage(
                    globalVariableMap
                )

        val targets = targets.map { it.build(Context(globalVariableMap), false) }

        return ScratchProject(
            targets = listOf(newStage) + targets
        )
    }
}

fun ProjectBuilder.addStage(target: TargetBuilder) {
    stage = target
}