package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class GoForwardBackwardLayersImport : ImportNode {
    override val opCode: String = "looks_goforwardbackwardlayers"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("go")
        builder.append(blockOr.fields["FORWARDBACKWARD"]?.get(0)?.capitalize())
        builder.append("Layers(")
        handle(builder, target, myList, blockOr.inputs["NUM"]?.get(1))
        builder.append(")\n")
    }

}

//looks_backdropnumbername

class BackdropNumberNameImport : ImportNode {
    override val opCode: String = "looks_backdropnumbername"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("backdrop")
        builder.append(blockOr.fields["NUMBER_NAME"]?.get(0)?.capitalize())
        builder.append("()\n")
    }

}