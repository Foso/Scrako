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

