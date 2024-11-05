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


//looks_changesizeby

class ChangeSizeByImport : ImportNode {
    override val opCode: String = "looks_changesizeby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("changeSizeBy(")
        builder.append(blockOr.inputs["CHANGE"]?.get(1))
        builder.append(")\n")
    }

}

//looks_nextcostume

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

//sensing_mousex

class MouseXImport : ImportNode {
    override val opCode: String = "sensing_mousex"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("mouseX()\n")
    }

}

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


//looks_goforwardbackwardlayers

class GoForwardBackwardLayersImport : ImportNode {
    override val opCode: String = "looks_goforwardbackwardlayers"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("goForwardBackwardLayers(")
        builder.append(blockOr.inputs["NUM"]?.get(1))
        builder.append(")\n")
    }

}