package de.jensklingenberg.newimport.event

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class WhenGreaterThanImport : ImportNode {
    override val opCode: String = "event_whengreaterthan"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenGreaterThan(")
        builder.append(blockFullOr.fields["WHENGREATERTHANMENU"]?.get(0))
        builder.append(", ")
        handle(builder, target, myList, blockFullOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}