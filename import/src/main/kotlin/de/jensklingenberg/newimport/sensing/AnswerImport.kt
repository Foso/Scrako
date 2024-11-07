package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class AnswerImport : ImportNode {
    override val opCode: String = "sensing_answer"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("Answer()")
    }
}


//sensing_loudness

class LoudnessImport : ImportNode {
    override val opCode: String = "sensing_loudness"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("Loudness")
    }
}

//sensing_timer

class TimerImport : ImportNode {
    override val opCode: String = "sensing_timer"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("Timer")
    }
}


//sensing_resettimer

class ResetTimerImport : ImportNode {
    override val opCode: String = "sensing_resettimer"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("resetTimer()")
    }
}

//sensing_of

class OfImport : ImportNode {
    override val opCode: String = "sensing_of"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("of(")
        builder.append(blockFullOr.fields["PROPERTY"]?.get(0))
        builder.append(",")
        handle(builder, target, myList, blockFullOr.inputs["OBJECT"]?.get(1))
        builder.append(")")
    }
}

//sensing_of_object_menu

class OfObjectMenuImport : ImportNode {
    override val opCode: String = "sensing_of_object_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {

        builder.append(blockFullOr.fields["OBJECT"]?.get(0))

    }
}

//sensing_current

class CurrentImport : ImportNode {
    override val opCode: String = "sensing_current"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("current(")
        builder.append(blockFullOr.fields["CURRENTMENU"]?.get(0))
        builder.append(")")
    }
}


//sensing_username

class UsernameImport : ImportNode {
    override val opCode: String = "sensing_username"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("Username")
    }
}
