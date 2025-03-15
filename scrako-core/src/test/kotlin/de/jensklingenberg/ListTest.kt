package de.jensklingenberg

import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createList
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class ListTest {

    @Test
    fun testGlobalList() {
        val builder = projectBuilder {
            val globalVar = createGlobalList("globalVar")

            spriteBuilder("Sprite1") {
                val localVar = createList("globalVar")
                scriptBuilder {

                }
            }
        }

        val project = builder.build("")
        assertEquals(0, project.targets.find { it.name == "Sprite1" }?.lists?.size)
        assertEquals(1, project.targets.find { it.name == "Stage" }?.lists?.size)
    }

    @Test
    fun testLocalList() {
        val builder = projectBuilder {

            spriteBuilder("Sprite1") {
                val localVar = createList("localVar")
            }
        }

        val project = builder.build("")
        assertEquals(1, project.targets.find { it.name == "Sprite1" }?.lists?.size)
        assertEquals(0, project.targets.find { it.name == "Stage" }?.lists?.size)
    }
}