package me.jens.targets

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.common.define
import de.jensklingenberg.scrako.common.functionBuilder
import de.jensklingenberg.scrako.common.getOrCreateVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.control.wait
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.procedures.Argument
import de.jensklingenberg.scratch.procedures.Argument2
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.ArgumentType
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import me.jens.sprite1
import java.util.UUID

fun ScriptBuilder.forEachIndexed(users: ScratchList, ff: ScriptBuilder.(index: ScratchVariable) -> Unit) {
    val index = getOrCreateVariable(UUID.randomUUID().toString())
    setVariable(index, IntBlock(0))
    repeat(lengthOfList(users)) {
        ff.invoke(this, index)
        changeVariable(index, 1.0)
        wait(0) // Why?
    }
    setVariable(index, IntBlock(0))
}

fun ScriptBuilder.readInput(){
    call("myFunction", emptyList()) //
}

fun ProjectBuilder.MyTarget(jensList: ScratchList) {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))

    val broadcast = Broadcast("hello")

    targetBuilder("Sprite1") {
        addSprite(sprite1)

        scriptBuilder {
            define("Hallo"){
                say("Hallo")
                say("Ciao")
            }

            define("2222"){
                say("Hallo")
                say("Ciao")
            }
        }

        scriptBuilder {
            val element = getOrCreateVariable("myVar")
            whenFlagClicked()
            //setVariable(element, IntBlock(1))
            changeVariable(element, 1.0)
            say(element)
            say("haallo")
            say("Ciao")
            forever {
                say("Ciao")
            }
            //say(showPickerAs("text"))
        }

    }
}
