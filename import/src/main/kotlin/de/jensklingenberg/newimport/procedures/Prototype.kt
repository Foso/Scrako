package de.jensklingenberg.newimport.procedures

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class PrototypeImport : ImportNode {
    override val opCode: String = "procedures_prototype"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {

        val split = blockFullOr.mutation?.proccode?.split("%")
        builder.append("\"" + blockFullOr.mutation?.proccode?.substringBefore(" %") + "\"")
        if (blockFullOr.mutation?.warp == "true") {
            builder.append(", refresh = true")
        }
        val args = blockFullOr.mutation?.argumentnames?.removePrefix("[")?.removeSuffix("]")?.split(",")
        if (args?.size!! > 1) {
            builder.append(", arguments = listOf(")

            args.forEachIndexed { index, s ->
                val fixed = s.removeSurrounding("\"")
                when (split?.drop(1)?.get(index)!!) {
                    "s" -> builder.append("Argument(\"$fixed\", ArgumentType.NUMBER_OR_TEXT),")
                    "b" -> builder.append("Argument(\"$fixed\", ArgumentType.Boolean),")
                    else -> {}
                }
            }
            builder.append(")")
        }
    }
}
