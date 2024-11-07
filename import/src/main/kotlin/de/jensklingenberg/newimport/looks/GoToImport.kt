package de.jensklingenberg.newimport.looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import java.util.Locale

class GoToImport : ImportNode {
    override val opCode: String = "looks_gotofrontback"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("goTo")
        builder.append(blockFullOr.fields["FRONT_BACK"]?.get(0)
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
        builder.append("()\n")
    }
}