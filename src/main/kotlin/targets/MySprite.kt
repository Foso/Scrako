package me.jens.targets

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.addSprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.common.getVariable
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.control.wait
import de.jensklingenberg.scratch.createList
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.sendBroadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.looks.Effect.GHOST
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.looks.changeEffectBy
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.looks.setEffectTo
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.SensingOptions.x_position
import de.jensklingenberg.scratch.sensing.of
import de.jensklingenberg.scratch.sound.clearEffects
import de.jensklingenberg.scratch.targetBuilder
import me.jens.sprite1
import me.jens.spriteArrow
import java.util.UUID


fun NodeBuilder.forEachIndexed(users: ScratchList, ff: NodeBuilder.(index: ScratchVariable) -> Unit) {
    val index = getVariable(UUID.randomUUID().toString())
    setVariable(index, IntBlock(0))
    repeat(lengthOfList(users)) {
        ff.invoke(this, index)
        changeVariable(index, 1.0)
        wait(0) // Why?
    }
    setVariable(index, IntBlock(0))
}

fun MyTarget(jensList: ScratchList): Target {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))


    val broadcast = Broadcast("hello")

    val target = targetBuilder {
        addSprite(sprite1)

        blockBuilder {
            whenFlagClicked()
            sendBroadcast(broadcast)
            clearEffects()
        }

        blockBuilder {
            val tt = getVariable("myVariable2")
            val users = createList("Users", listOf("Jens", "Martin", "Thomas"))
            val index = getVariable("index")
            whenIRecieve(broadcast)
            forEachIndexed(users) {
                move(10)
            }

        }
    }

    return target.build()
}

private operator fun String.unaryPlus(): ReporterBlock {
    return StringBlock(this)
}

