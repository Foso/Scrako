package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scrako.common.Target
import de.jensklingenberg.scrako.common.createBlocks23
import de.jensklingenberg.scrako.common.createTarget

class TargetBuilder {
   internal val scriptBuilders = mutableListOf<ScriptBuilder>()
    var sprite: Sprite? = null
    var name: String = ""

    fun build(context: Context, isStage: Boolean): Target {
        val ww = scriptBuilders.map { it.childs }

        ww.flatten().forEach {
            if(isStage && it is MotionBlock){
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariabless = context.variableMap.toMutableMap()
        scriptBuilders.forEach {
            it.variableMap.values.forEach { scriptVariable ->
                if (allVariabless.entries.none { scriptVariable.name == it.key }) {
                   allVariabless[scriptVariable.name] = scriptVariable
                }
            }
        }

        val targetVariables = scriptBuilders.asSequence().map { it.variableMap.values }
            .flatten().toMutableSet().map { scriptVariable ->
                if (context.variableMap.values.any {
                        scriptVariable.name == it.name
                    }) {
                    null
                } else {
                    scriptVariable
                }
            }.filterNotNull().toSet()

        val blocks = createBlocks23(ww, context.copy(variableMap = allVariabless))

        return createTarget(
            blocks,
            this.sprite!!,
            emptyList(),
            this.scriptBuilders.map { it.lists }.flatten().toSet(),
            targetVariables
        )
    }
}

fun TargetBuilder.addSprite(sprite: Sprite) {
    this.sprite = sprite
}