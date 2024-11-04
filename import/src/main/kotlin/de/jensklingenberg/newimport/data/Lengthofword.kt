package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.imports.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

public class LengthofWordImport : ImportNode {
    override val opCode: String = "operator_length"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("lengthoflist(")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(")")
    }
}
