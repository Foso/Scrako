package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class GoForwardBackwardLayersImport : ImportNode {
    override val opCode: String = "looks_goforwardbackwardlayers"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("go")
        builder.append(blockFullOr.fields["FORWARDBACKWARD"]?.get(0)?.capitalize())
        builder.append("Layers(")
        handle(builder, target, myList, blockFullOr.inputs["NUM"]?.get(1))
        builder.append(")\n")
    }

}

//looks_backdropnumbername

