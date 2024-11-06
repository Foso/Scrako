package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block

class DistanceToMenu(private val destination: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "sensing_distancetomenu",
            fields = mapOf("DISTANCETOMENU" to listOf(destination, null))
        ).toBlock(nextUUID, parent)
    }
}

//sensing_current

class Current(val s: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "sensing_current",
            fields = mapOf("CURRENTMENU" to listOf(s, null))
        ).toBlock(nextUUID, parent)
    }
}

enum class CurrentMenu(val value: String) {
    YEAR("YEAR"),
    MONTH("MONTH"),
    DATE("DATE"),
    DAYOFWEEK("DAYOFWEEK"),
    HOUR("HOUR"),
    MINUTE("MINUTE"),
    SECOND("SECOND")
}

fun current(s: CurrentMenu): ReporterBlock = Current(s.value.lowercase())