package de.jensklingenberg

import de.jensklingenberg.scrako.builder.getOrCreateGlobalList
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class ListTest {

    @Test
    fun testGlobalList() {
        val builder = projectBuilder {
            val globalVar = getOrCreateGlobalList("globalVar")

            spriteBuilder("Sprite1") {
                val localVar = getOrCreateList("globalVar")
                scriptBuilder {

                }
            }
        }

        val project = builder.build()
        assertEquals(0, project.targets.find { it.name == "Sprite1" }?.lists?.size)
        assertEquals(1, project.targets.find { it.name == "Stage" }?.lists?.size)
    }

    @Test
    fun testLocalList() {
        val builder = projectBuilder {

            spriteBuilder("Sprite1") {
                val localVar = getOrCreateList("localVar")
            }
        }

        val project = builder.build()
        assertEquals(1, project.targets.find { it.name == "Sprite1" }?.lists?.size)
        assertEquals(0, project.targets.find { it.name == "Stage" }?.lists?.size)
    }
}