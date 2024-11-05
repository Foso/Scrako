package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class KeyoptionsImport : ImportNode {
    override val opCode: String = "sensing_keyoptions"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("\"" + blockOr.fields["KEY_OPTION"]?.get(0) + "\"")
    }
}
