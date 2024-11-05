package de.jensklingenberg.newimport.procedures

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class PrototypeImport : ImportNode {
    override val opCode: String = "procedures_prototype"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {

        val split = blockOr.mutation?.proccode?.split("%")
        builder.append("\""+blockOr.mutation?.proccode?.substringBefore(" %")+"\"")
        if (blockOr.mutation?.warp == "true") {
            builder.append(", refresh = true")
        }
        val args = blockOr.mutation?.argumentnames?.removePrefix("[")?.removeSuffix("]")?.split(",")
        if(args?.size!! >1){
            builder.append(", arguments = listOf(")

            args.forEachIndexed { index, s ->
                val fixed = s.removeSurrounding("\"")
                when(split?.drop(1)?.get(index)!!){
                    "s" -> builder.append("Argument(\"$fixed\", ArgumentType.NUMBER_OR_TEXT),")
                    "b" -> builder.append("Argument(\"$fixed\", ArgumentType.Boolean),")
                    else->{}
                }
            }
            builder.append(")")
        }
    }
}
