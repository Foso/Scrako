package looks

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SwitchbackdroptoImport : ImportNode {
    override val opCode: String = "looks_switchbackdropto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("switchbackdropto()\n")
    }
}
