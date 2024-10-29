package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchProject
import de.jensklingenberg.scrako.common.defaultStage
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<TargetBuilder>()
    internal var stage: TargetBuilder? = null
    private var globalVariableMap = mutableMapOf<String, UUID>()
    private var lists = mutableMapOf<String, ScratchList>()
    fun addVariable(name: String) {
        globalVariableMap[name] = UUID.randomUUID()
    }

    fun addList(list: ScratchList) {
        lists[list.name] = list
    }

    fun build(): ScratchProject {

        val newStage =
            stage?.build(Context(globalVariableMap, lists), true)?.copy(isStage = true, visible = false, layerOrder = 0)
                ?: defaultStage(
                    globalVariableMap
                )

        val targets = targets.map { it.build(Context(globalVariableMap, lists), false) }

        return ScratchProject(
            targets = listOf(newStage) + targets
        )
    }


}

fun ProjectBuilder.addStage(target: TargetBuilder) {
    stage = target
}