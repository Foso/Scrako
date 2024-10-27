package de.jensklingenberg.scrako.common

class TargetBuilder {
    val scriptBuilders = mutableListOf<ScriptBuilder>()
    var sprite: Sprite? = null

    fun build(context: Context): Target {
        val ww = scriptBuilders.map { it.childs }

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

        return createTarget(
            createBlocks23(ww, context.copy(variables = allVariabless)),
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