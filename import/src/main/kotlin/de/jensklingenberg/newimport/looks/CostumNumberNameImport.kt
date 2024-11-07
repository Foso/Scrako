package de.jensklingenberg.newimport.looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.newimport.operator.LooksBlock
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target
import java.util.Locale

class CostumNumberNameImport : ImportNode, LooksBlock {
    override val opCode: String = "looks_costumenumbername"
    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("costume")
        builder.append(blockFullOr.fields["NUMBER_NAME"]?.get(0)
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
        builder.append("()")
    }

}