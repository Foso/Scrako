package de.jensklingenberg.newimport.procedures

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class CallImport : ImportNode {
    override val opCode: String = "procedures_call"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        when (blockFullOr.mutation?.proccode) {
            "​​log​​ %s" -> {
                builder.append("log(")
                handle(builder, target, myList, blockFullOr.inputs["arg0"]?.get(1))
                builder.append(")\n")
            }

            else -> {
                builder.append("call(\"${blockFullOr.mutation?.proccode?.substringBefore("%")?.trimEnd()}\"")
                //handle(builder, target, myList,  blockOr.inputs["arg0"]?.get(1))
                builder.append(", listOf(")
                blockFullOr.inputs.forEach { (t, u) ->
                    handle(builder, target, myList, u.get(1))
                }
                builder.append("))\n")
            }
        }

    }

}

