package de.jensklingenberg.de.jensklingenberg.example.procedures

import de.jensklingenberg.imports.ImportNode
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
        when(blockOr.mutation?.proccode) {
            "​​log​​ %s" -> builder.append("log(${blockOr.inputs["arg0"]?.get(0)})\n")
            else -> builder.append("call(${blockOr.mutation?.proccode})\n")
        }

    }

}