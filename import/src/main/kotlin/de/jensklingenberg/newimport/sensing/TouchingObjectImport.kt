package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
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
        blockId: String,
    ) {
        builder.append("touchingObject(")
        handle(builder, target, myList, blockOr.inputs["TOUCHINGOBJECTMENU"]?.get(1))
        builder.append(")\n")
    }
}


//sensing_touchingcolor
class TouchingColorImport : ImportNode {
    override val opCode: String = "sensing_touchingcolor"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("touchingColor(")
        handle(builder, target, myList, blockOr.inputs["COLOR"]?.get(1))
        builder.append(")\n")
    }
}


//sensing_coloristouchingcolor

class ColorIsTouchingColorImport : ImportNode {
    override val opCode: String = "sensing_coloristouchingcolor"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("colorIsTouchingColor(")
        handle(builder, target, myList, blockOr.inputs["COLOR"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockOr.inputs["COLOR2"]?.get(1))
        builder.append(")\n")
    }
}

//sensing_distanceto

class DistanceToImport : ImportNode {
    override val opCode: String = "sensing_distanceto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("distanceTo(")
        handle(builder, target, myList, blockOr.inputs["DISTANCETOMENU"]?.get(1))
        builder.append(")\n")
    }
}


//sensing_setdragmode

class SetDragModeImport : ImportNode {
    override val opCode: String = "sensing_setdragmode"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("setDragMode(")
        builder.append(blockOr.fields["DRAG_MODE"]?.get(0))
        builder.append(")\n")
    }
}

