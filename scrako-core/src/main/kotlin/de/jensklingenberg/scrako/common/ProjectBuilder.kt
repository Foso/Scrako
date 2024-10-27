package de.jensklingenberg.scrako.common

class ProjectBuilder {
    internal val targets = mutableListOf<TargetBuilder>()
    internal var stage: TargetBuilder? = null
    private var variables = mutableSetOf<ScratchVariable>()


    fun addVariable(name: ScratchVariable) {
        variables.add(name)
    }

    fun build(): ScratchProject {

        val newStage =
            stage?.build(Context(variables),true)?.copy(isStage = true, visible = false, layerOrder = 0) ?: defaultStage(
                variables.toList()
            )

        val targets = targets.map { it.build(Context(variables),false) }

        return ScratchProject(
            targets = listOf(newStage) + targets
        )
    }
}

fun ProjectBuilder.addStage(target: TargetBuilder) {
    stage = target
}