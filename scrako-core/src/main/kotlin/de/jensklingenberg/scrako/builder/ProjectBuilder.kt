package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.defaultStage
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<TargetBuilder>()
    internal var stage: TargetBuilder? = null
    private var globalVariableMap = mutableMapOf<String, UUID>()
    private var lists = mutableMapOf<String, ScratchList>()
    private var broadcasts = mutableListOf<Broadcast>()
    fun addVariable(name: String) {
        globalVariableMap[name] = UUID.randomUUID()
    }

    fun addList(list: ScratchList) {
        lists[list.name] = list
    }

    fun build(): ScratchProject {

        val broadcasts1 = broadcasts.associate { it.id.toString() to it.name }
        val newStage =
            stage?.build(Context(globalVariableMap, lists, emptyList(),broadcasts1), true)
                ?.copy(isStage = true, visible = false, layerOrder = 0)
                ?: defaultStage(
                    globalVariableMap,
                    broadcasts1
                )

        val targets = targets.map { it.build(Context(globalVariableMap, lists, emptyList(), broadcasts1), false) }

        return ScratchProject(
            targets = listOf(newStage) + targets
        )
    }

    internal fun addBroadcast(board: Broadcast) {
        broadcasts.add(board)
    }


}

fun ProjectBuilder.addStage(target: TargetBuilder) {
    stage = target
}

fun ProjectBuilder.createBroadcast(s: String): Broadcast {
    val board = Broadcast(s)
    addBroadcast(board)
    return board
}


fun projectBuilder(ff: ProjectBuilder.() -> Unit): ProjectBuilder {
    val node = ProjectBuilder()
    ff.invoke(node)
    return node
}

fun ProjectBuilder.stageBuilder(ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)
    targetBuilder.name = "Stage"
    addStage(targetBuilder)
    return targetBuilder
}

fun ProjectBuilder.targetBuilder(name: String, ff: TargetBuilder.() -> Unit): TargetBuilder {
    val targetBuilder = TargetBuilder()
    ff.invoke(targetBuilder)
    targetBuilder.name = name
    targets.add(targetBuilder)
    return targetBuilder
}

fun TargetBuilder.scriptBuilder(builder: ScriptBuilder.() -> Unit): ScriptBuilder {
    val scriptBuilder = ScriptBuilder()
    builder.invoke(scriptBuilder)
    scriptBuilders.add(scriptBuilder)
    return scriptBuilder
}


fun ProjectBuilder.getGlobalVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}

fun ProjectBuilder.getOrCreateGlobalList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}