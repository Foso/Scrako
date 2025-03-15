package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class Mathop(val block0: ReporterBlock, val operator: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "operator_mathop",
            inputs = mapOf(
                "NUM" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(
                "OPERATOR" to listOf(operator, null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

enum class MathOptions(val operator: String) {
    ABS("abs"),
    FLOOR("floor"),
    CEILING("ceiling"),
    SQRT("sqrt"),
    SIN("sin"),
    COS("cos"),
    TAN("tan"),
    ASIN("asin"),
    ACOS("acos"),
    ATAN("atan"),
    LN("ln"),
    LOG("log"),
    EEXP("e ^"),
    TENEXP("10 ^")
}

fun mathop(operator: MathOptions, block0: ReporterBlock): ReporterBlock = Mathop(block0, operator.operator)