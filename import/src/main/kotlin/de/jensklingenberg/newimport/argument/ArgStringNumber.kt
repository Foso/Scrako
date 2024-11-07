package de.jensklingenberg.newimport.argument

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target


class ArgStringNumber : ImportNode {
    override val opCode: String = "argument_reporter_string_number"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("\"" + blockFullOr.fields["VALUE"]?.get(0) + "\"")

    }
}

class ArgBoolean : ImportNode {
    override val opCode: String = "argument_reporter_boolean"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append(blockFullOr.fields["VALUE"]?.get(0))
    }
}