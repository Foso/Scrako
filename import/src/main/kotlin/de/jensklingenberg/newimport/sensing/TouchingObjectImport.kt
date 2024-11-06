package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class TouchingObjectImport : ImportNode {
    override val opCode: String = "sensing_touchingobject"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("touchingObject(")
        builder.append(blockOr.fields["TOUCHINGOBJECTMENU"]?.get(0))
        builder.append(")\n")
    }

}


//sensing_mousex

class MouseYImport : ImportNode {
    override val opCode: String = "sensing_mousey"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("mouseY()\n")
    }

}

