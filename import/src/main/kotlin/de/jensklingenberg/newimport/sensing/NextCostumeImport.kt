package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class NextCostumeImport : ImportNode {
    override val opCode: String = "looks_nextcostume"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("nextCostume()\n")
    }

}

//looks_switchbackdroptoandwait
class SwitchBackdropAndWaitImport : ImportNode {
    override val opCode: String = "looks_switchbackdroptoandwait"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("switchBackdropAndWait(")
        builder.append(blockOr.fields["BACKDROP"]?.get(0))
        builder.append(")\n")
    }

}