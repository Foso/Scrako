package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
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

    fun addVariable(name: String) {
        variableMap[name] = UUID.randomUUID()
    }

    fun build(context: Context, isStage: Boolean): Target {
        val ww = scriptBuilders.map { it.childs }

        ww.flatten().forEach {
            if (isStage && it is MotionBlock) {
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariabless = variableMap + context.variableMap

        val targetVariabes = variableMap.map {
            if(context.variableMap.containsKey(it.key)){
                it.key to null
            }else{
                it.key to it.value
            }

        }.toMap().filter { it.value != null }.map { it.key to it.value!! }.toMap()
        val blocks = createBlocks23(ww, context.copy(variableMap = allVariabless))

        return createTarget(
            blocks,
            this.sprite!!,
            emptyList(),
            this.scriptBuilders.map { it.lists }.flatten().toSet(),
            targetVariabes
        )
    }
}

fun TargetBuilder.addSprite(sprite: Sprite) {
    this.sprite = sprite
}