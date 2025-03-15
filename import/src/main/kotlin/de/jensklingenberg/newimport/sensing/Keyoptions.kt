package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class KeyoptionsImport : ImportNode {
    override val opCode: String = "sensing_keyoptions"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("\"" + blockFullOr.fields["KEY_OPTION"]?.get(0) + "\"")
    }
}
