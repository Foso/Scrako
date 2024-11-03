package de.jensklingenberg

import de.jensklingenberg.scrako.builder.getGlobalVariable
import de.jensklingenberg.scrako.builder.addSpriteVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class VariablesTest {

    @Test
    fun testGlobalVariables() {
        val builder = projectBuilder {
            val globalVar = getGlobalVariable("globalVar")

            spriteBuilder("Sprite1") {
                val localVar = addSpriteVariable("globalVar")
                scriptBuilder {

                }
            }
        }

        val project = builder.build()
        assertEquals(0, project.targets.find { it.name == "Sprite1" }?.variables?.size)
        assertEquals(1, project.targets.find { it.name == "Stage" }?.variables?.size)
    }

    @Test
    fun testLocalVariables() {
        val builder = projectBuilder {

            spriteBuilder("Sprite1") {
                val localVar = addSpriteVariable("localVar")
            }
        }

        val project = builder.build()
        assertEquals(1, project.targets.find { it.name == "Sprite1" }?.variables?.size)
        assertEquals(0, project.targets.find { it.name == "Stage" }?.variables?.size)
    }
}