package me.jens.targets

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scratch.addSprite
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.getVariable
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.control.wait
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.scriptBuilder
import de.jensklingenberg.scratch.targetBuilder
import me.jens.sprite1
import java.util.UUID
import de.jensklingenberg.scrako.common.Target
import me.jens.files.showPickerAs

fun ScriptBuilder.forEachIndexed(users: ScratchList, ff: ScriptBuilder.(index: ScratchVariable) -> Unit) {
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

        scriptBuilder {

            whenFlagClicked()
            say(showPickerAs("text"))

        }

    }

    return target.build()
}

private operator fun String.unaryPlus(): ReporterBlock {
    return StringBlock(this)
}

