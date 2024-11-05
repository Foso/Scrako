package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target
import java.lang.StringBuilder

public class SetEffectToImport : ImportNode {
    override val opCode: String = "looks_seteffectto"

    override fun visit(
        builder: StringBuilder,
        scratchProject: ScratchProject,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setEffectTo(")
        handle(builder, target, myList, scratchProject, block.inputs["VALUE"]?.get(1))
        builder.append(",")
        builder.append(block.fields["EFFECT"]?.get(0))
        builder.append(")\n")
    }
}