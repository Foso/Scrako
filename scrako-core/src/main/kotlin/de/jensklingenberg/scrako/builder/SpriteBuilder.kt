package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.createTarget
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Target
import java.util.UUID

open class SpriteBuilder {
    internal val scriptBuilders = mutableListOf<ScriptBuilder>()
    var name: String = ""
    private val variableMap = mutableMapOf<String, UUID>()
    private val listMap = mutableMapOf<String, ScratchList>()
    private val costumesList = mutableListOf<Costume>()
    private var position = Pair(0.0, 0.0)
    private var sounds = mutableListOf<String>()

    fun addSounds(sound: List<String>) {
        sounds.addAll(sound)
    }

    fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
    }

    fun addPosition(y: Double, x: Double) {
        position = Pair(y, x)
    }

    fun addList(list: ScratchList) {
        listMap[list.name] = list
    }

    internal fun build(context: Context, isStage: Boolean): Target {
        val functionsMap = mutableListOf<Argumenti>()
        scriptBuilders.forEach {
            it.functionsMap.forEach { function ->
                function.value.forEach { argument2 ->
                    functionsMap.add(
                        Argumenti(
                            argument2.name,
                            UUID.randomUUID().toString(),
                            function.key,
                            argument2.type
                        )
                    )
                }

            }

        }

        val nodeListList = scriptBuilders.map { it.childs }

        nodeListList.flatten().forEach {
            if (isStage && it is MotionBlock) {
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariables = variableMap + context.variableMap
        var allLists = listMap


        val targetVariables = variableMap.map {
            if (context.variableMap.containsKey(it.key)) {
                it.key to null
            } else {
                it.key to it.value
            }
        }.toMap()
            .filter { it.value != null }
            .map { it.key to it.value!! }.toMap()

        val targetLists = listMap.map {
            if (context.variableMap.containsKey(it.key)) {
                it.key to null
            } else {
                it.key to it.value
            }
        }.toMap()
            .filter { it.value != null }
            .map { it.key to it.value!! }.toMap()
        val blocks = createBlocks23(
            nodeListList,
            Context(
                variableMap = allVariables,
                lists = allLists,
                functions = functionsMap,
                broadcasts1 = context.broadcasts1
            )
        )

        return createTarget(
            blocks = blocks,
            name = name,
            comments = emptyList(),
            lists = targetLists,
            variables = targetVariables,
            costumes = costumesList,
            broadcasts = context.broadcasts1,
            sounds = emptyList(),
            size = 100,
            x = position.second,
            y = position.first
        )
    }

    fun addCostumeList(costume: Costume) {
        costumesList.add(costume)
    }

}

fun SpriteBuilder.addCostumes(costume: List<Costume>) {
    costume.forEach { addCostumeList(it) }
}

fun SpriteBuilder.getOrCreateList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}

fun SpriteBuilder.getOrCreateVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}

