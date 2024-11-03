package de.jensklingenberg.looks

import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scratch3.looks.say
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Test


class SayTest {

    @Test
    fun testSay() {

        val builder = projectBuilder {
            spriteBuilder("Sprite1") {
                scriptBuilder {
                    say("Hello")
                }
            }
        }

        val project = builder.build()
        val blo = project.targets[1].blocks
        val sayBlock = blo.entries.first()
        assertEquals("looks_say", sayBlock.value.opcode)
        assertEquals(
            "Hello",
            (sayBlock.value.inputs["MESSAGE"]?.get(1) as JsonArray)[1].jsonPrimitive.contentOrNull
        )
    }
}