package de.jensklingenberg.newimport.event

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class WhenKey : ImportNode {
    override val opCode: String = "event_whenkeypressed"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenKeyPressed(Key.${blockOr.fields["KEY_OPTION"]?.get(0)})\n")
    }
}

//event_whenthisspriteclicked

class WhenThisSpriteClicked : ImportNode {
    override val opCode: String = "event_whenthisspriteclicked"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenThisSpriteClicked()\n")
    }
}

//event_whenstageclicked

class WhenStageClickedImport : ImportNode {
    override val opCode: String = "event_whenstageclicked"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenStageClicked()\n")
    }
}

//event_whenbackdropswitchesto

class WhenBackdropSwitchesToImport : ImportNode {
    override val opCode: String = "event_whenbackdropswitchesto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenBackdropSwitchesTo(${blockOr.fields["BACKDROP_OPTION"]?.get(0)})\n")
    }
}


//event_whengreaterthan

class WhenGreaterThanImport : ImportNode {
    override val opCode: String = "event_whengreaterthan"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("whenGreaterThan(")
        builder.append(blockOr.fields["WHENGREATERTHANMENU"]?.get(0))
        builder.append(", ")
        handle(builder, target, myList, blockOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}