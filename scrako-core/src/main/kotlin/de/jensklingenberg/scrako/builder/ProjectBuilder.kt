package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.defaultStage
import de.jensklingenberg.scrako.model.Meta
import de.jensklingenberg.scrako.model.ScratchProject
import java.util.UUID

class ProjectBuilder {
    internal val targets = mutableListOf<CommonSpriteBuilder>()
    internal var stage: CommonSpriteBuilder? = null
    private var globalVariableMap = mutableMapOf<String, UUID>()
    private var lists = mutableMapOf<String, ScratchList>()
    private var broadcasts = mutableListOf<Broadcast>()
    private var meta = Meta(
        semver = "3.0.0",
        vm = "0.2.0",
        agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
    )

    fun addVariable(name: ScratchVariable) {
        globalVariableMap[name.name] = UUID.randomUUID()
    }

    fun addMeta(meta: Meta) {
        this.meta = meta
    }

    fun addList(list: ScratchList) {
        lists[list.name] = list
    }

    fun build(): ScratchProject {

        val broadcasts1 = broadcasts.associate { it.name to UUID.randomUUID().toString() }
        val newStage =
            stage?.build(Context(globalVariableMap, lists, emptyList(), broadcasts1), true)
                ?.copy(isStage = true, visible = false, layerOrder = 0)
                ?: defaultStage(
                    globalVariableMap,
                    broadcasts1,
                    lists
                )

        val targets = targets.map { it.build(Context(globalVariableMap, lists, emptyList(), broadcasts1), false) }

        return ScratchProject(
            targets = listOf(newStage) + targets, meta = meta
        )
    }

    internal fun addBroadcast(board: Broadcast) {
        broadcasts.add(board)
    }

}

fun ProjectBuilder.addStage(target: CommonSpriteBuilder) {
    stage = target
}

fun ProjectBuilder.createBroadcast(name: String): Broadcast {
    val board = Broadcast(name)
    addBroadcast(board)
    return board
}


fun projectBuilder(projectBuilderScope: ProjectBuilder.() -> Unit): ProjectBuilder {
    val node = ProjectBuilder()
    projectBuilderScope.invoke(node)
    return node
}

fun ProjectBuilder.stageBuilder(spriteBuilderScope: StageSpriteBuilder.() -> Unit): CommonSpriteBuilder {
    val spriteBuilder = StageSpriteBuilder()
    spriteBuilderScope.invoke(spriteBuilder)
    spriteBuilder.name = "Stage"
    addStage(spriteBuilder)
    return spriteBuilder
}

fun ProjectBuilder.spriteBuilder(
    name: String,
    commonSpriteBuilderScope: SpriteBuilder.() -> Unit
): CommonSpriteBuilder {
    val commonSpriteBuilder = SpriteBuilder()
    commonSpriteBuilderScope.invoke(commonSpriteBuilder)
    commonSpriteBuilder.name = name
    targets.add(commonSpriteBuilder)
    return commonSpriteBuilder
}

fun SpriteBuilder.scriptBuilder(builder: SpriteScriptBuilder.() -> Unit): CommonScriptBuilder {
    val spriteScriptBuilder = SpriteScriptBuilder()
    builder.invoke(spriteScriptBuilder)
    commonScriptBuilders.add(spriteScriptBuilder)
    return spriteScriptBuilder
}

fun StageSpriteBuilder.scriptBuilder(builder: StageScriptBuilder.() -> Unit): CommonScriptBuilder {
    val spriteScriptBuilder = StageScriptBuilder()
    builder.invoke(spriteScriptBuilder)
    commonScriptBuilders.add(spriteScriptBuilder)
    return spriteScriptBuilder
}


fun ProjectBuilder.createGlobalVariable(name: String, isCloud: Boolean = false): ScratchVariable {
    val element = ScratchVariable(name, isCloud)
    addVariable(element)
    return element
}

fun ProjectBuilder.createGlobalList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}