package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.defaultStage
import de.jensklingenberg.scrako.model.ScratchProject
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<SpriteBuilder>()
    internal var stage: SpriteBuilder? = null
    private var globalVariableMap = mutableMapOf<String, UUID>()
    private var lists = mutableMapOf<String, ScratchList>()
    private var broadcasts = mutableListOf<Broadcast>()

    fun addVariable(name: ScratchVariable) {
        globalVariableMap[name.name] = UUID.randomUUID()
    }

    fun addList(list: ScratchList) {
        lists[list.name] = list
    }

    fun build(): ScratchProject {

        val broadcasts1 = broadcasts.associate { it.id.toString() to it.name }
        val newStage =
            stage?.build(Context(globalVariableMap, lists, emptyList(), broadcasts1), true)
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

fun ProjectBuilder.addStage(target: SpriteBuilder) {
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

fun ProjectBuilder.stageBuilder(spriteBuilderScope: SpriteBuilder.() -> Unit): SpriteBuilder {
    val spriteBuilder = SpriteBuilder()
    spriteBuilderScope.invoke(spriteBuilder)
    spriteBuilder.name = "Stage"
    addStage(spriteBuilder)
    return spriteBuilder
}

fun ProjectBuilder.spriteBuilder(name: String, spriteBuilderScope: SpriteBuilder.() -> Unit): SpriteBuilder {
    val spriteBuilder = SpriteBuilder()
    spriteBuilderScope.invoke(spriteBuilder)
    spriteBuilder.name = name
    targets.add(spriteBuilder)
    return spriteBuilder
}

fun SpriteBuilder.scriptBuilder(builder: ScriptBuilder.() -> Unit): ScriptBuilder {
    val scriptBuilder = ScriptBuilder()
    builder.invoke(scriptBuilder)
    scriptBuilders.add(scriptBuilder)
    return scriptBuilder
}


fun ProjectBuilder.getGlobalVariable(name: String, isCloud: Boolean = false): ScratchVariable {
    val element = ScratchVariable(name, isCloud)
    addVariable(element)
    return element
}

fun ProjectBuilder.getOrCreateGlobalList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}