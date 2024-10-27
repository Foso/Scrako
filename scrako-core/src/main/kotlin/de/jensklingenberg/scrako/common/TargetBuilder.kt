package de.jensklingenberg.scrako.common

class TargetBuilder {
   internal val scriptBuilders = mutableListOf<ScriptBuilder>()
    var sprite: Sprite? = null

    fun build(context: Context, isStage: Boolean): Target {
        val ww = scriptBuilders.map { it.childs }

        ww.flatten().forEach {
            if(it is MotionBlock){
                throw IllegalArgumentException("MotionBlock for Stage not allowed")
            }
        }

        val allVariabless = context.variables.toMutableSet()
        scriptBuilders.forEach {
            it.variables.forEach { scriptVariable ->
                if (allVariabless.none { scriptVariable.name == it.name }) {
                    allVariabless.add(scriptVariable)
                }
            }
        }

        val targetVariables = scriptBuilders.asSequence().map { it.variables }
            .flatten().toMutableSet().map { scriptVariable ->
                if (context.variables.any {
                        scriptVariable.name == it.name
                    }) {
                    null
                } else {
                    scriptVariable
                }
            }.filterNotNull().toSet()

        val blocks = createBlocks23(ww, context.copy(variables = allVariabless))

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