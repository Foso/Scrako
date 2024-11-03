package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.createTarget
import de.jensklingenberg.scrako.model.Backdrop
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.model.Target
import java.util.UUID

open class CommonSpriteBuilder {
    internal val commonScriptBuilders = mutableListOf<CommonScriptBuilder>()
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
        commonScriptBuilders.forEach {
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

        val nodeListList = commonScriptBuilders.map { it.childs }

        nodeListList.flatten().forEach {
            if (isStage && it is MotionBlock) {
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariables = variableMap + context.variableMap
        var allLists = listMap+ context.lists


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
            if (context.lists.containsKey(it.key)) {
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
                broadcastMap = context.broadcastMap
            )
        )

        return createTarget(
            blocks = blocks,
            name = name,
            comments = emptyList(),
            lists = targetLists,
            variables = targetVariables,
            costumes = costumesList,
            broadcasts = context.broadcastMap,
            sounds = emptyList(),
            size = 100.0,
            x = position.second,
            y = position.first,
            visible = true,
            direction = 90.0,
            isStage = isStage
        )
    }

    fun addCostumeList(costume: Costume) {
        costumesList.add(costume)
    }

}

class StageSpriteBuilder : CommonSpriteBuilder()
class SpriteBuilder : CommonSpriteBuilder()

fun SpriteBuilder.addCostumes(costume: List<Costume>) {
    costume.forEach { addCostumeList(it) }
}

fun StageSpriteBuilder.addBackdrop(costume: List<Backdrop>) {
    costume.forEach { addCostumeList(it) }
}

fun CommonSpriteBuilder.createList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}

fun CommonSpriteBuilder.createVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}

