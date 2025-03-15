package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

//looks_switchbackdroptoandwait
class SwitchBackdropAndWaitImport : ImportNode {
    override val opCode: String = "looks_switchbackdroptoandwait"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("switchBackdropAndWait(")
        builder.append(blockFullOr.fields["BACKDROP"]?.get(0))
        builder.append(")\n")
    }

}