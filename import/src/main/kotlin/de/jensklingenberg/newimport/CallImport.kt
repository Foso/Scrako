package de.jensklingenberg.newimport

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class CallImport : ImportNode {
    override val opCode: String = "procedures_call"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        when (blockOr.mutation?.proccode) {
            "​​log​​ %s" -> {
                builder.append("log(")
                handle(builder, target, myList, project, blockOr.inputs["arg0"]?.get(1))
                builder.append(")\n")
            }

            else -> builder.append("call(${blockOr.mutation?.proccode})\n")
        }

    }

}

