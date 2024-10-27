package me.jens.targets

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ProjectBuilder
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.addSprite
import de.jensklingenberg.scrako.common.getVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.control.wait
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import me.jens.sprite1
import java.util.UUID

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

fun ProjectBuilder.MyTarget(jensList: ScratchList) {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))

    val broadcast = Broadcast("hello")

    targetBuilder {
        addSprite(sprite1)

        scriptBuilder {
            val element = getVariable("myVar")
            whenFlagClicked()
            setVariable(element, IntBlock(1))
            say(element)

            //say(showPickerAs("text"))
        }

    }
}
