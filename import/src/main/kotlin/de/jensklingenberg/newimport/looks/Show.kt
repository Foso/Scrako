package looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ShowImport : ImportNode {
    override val opCode: String = "looks_show"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("show()\n")
    }
}
