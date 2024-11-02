package de.jensklingenberg.imports

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject

class CallImport : ImportNode {
    override val opCode: String = "procedures_call"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        blockOr: Block,
        myList: MutableList<ImportNode>,
        t: String
    ) {
        when(blockOr.mutation?.proccode) {
            "​​log​​ %s" -> builder.append("log(${blockOr.inputs["arg0"]?.get(0)})\n")
            else -> builder.append("call(${blockOr.mutation?.proccode})\n")
        }

    }

}

