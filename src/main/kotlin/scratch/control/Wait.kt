package me.jens.scratch.control

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode

class Wait(seconds: Int) : BlockSpecSpec(
    opcode = OpCode.ControlWait,
    inputs = mapOf(
        "DURATION" to createDuration(seconds.toString())
    )
)

private fun createDuration(message: String = "1") = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(5),
                JsonPrimitive(message)
            )
        )
    )
)