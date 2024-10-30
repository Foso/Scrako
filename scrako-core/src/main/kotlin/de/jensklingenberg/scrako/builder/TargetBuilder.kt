package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Costume
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scrako.common.Target
import de.jensklingenberg.scrako.common.createBlocks23
import de.jensklingenberg.scrako.common.createTarget
import java.util.UUID

class TargetBuilder {
    internal val scriptBuilders = mutableListOf<ScriptBuilder>()
    var sprite: Sprite? = null
    var name: String = ""
    private val variableMap = mutableMapOf<String, UUID>()
    private val listMap = mutableMapOf<String, ScratchList>()
    private val costumesList = mutableListOf<Costume>()
    fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
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
            blocks,
            this.sprite!!,
            name,
            emptyList(),
            targetLists,
            targetVariables,
            costumesList,
            context.broadcasts1
        )
    }

    fun addCostumeList(costume: Costume) {
        costumesList.add(costume)
    }

}

fun TargetBuilder.addCostume(costume: Costume) {
    this.addCostumeList(costume)
}

fun TargetBuilder.addSprite(sprite: Sprite) {
    this.sprite = sprite
}

fun TargetBuilder.getOrCreateList(name: String, contents: List<String> = emptyList()): ScratchList {
    val element = ScratchList(name, contents)
    addList(element)
    return element
}

fun TargetBuilder.getOrCreateVariable(name: String): ScratchVariable {
    val element = ScratchVariable(name)
    addVariable(name)
    return element
}