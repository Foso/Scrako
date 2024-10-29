package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ScratchList
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

    fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
    }

    fun addList(list: ScratchList) {
        listMap[list.name] = list
    }

    internal fun build(context: Context, isStage: Boolean): Target {
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
        val blocks = createBlocks23(nodeListList, context.copy(variableMap = allVariables, lists = allLists))

        return createTarget(
            blocks,
            this.sprite!!,
            emptyList(),
            targetLists,
            targetVariables
        )
    }
}

fun TargetBuilder.addSprite(sprite: Sprite) {
    this.sprite = sprite
}