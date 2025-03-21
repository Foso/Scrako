package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID


abstract class Operator(
    private val operand1: List<ReporterBlock>,
    private val inputKeys: List<String>,
    private val opCode: String
) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val operatorUUID1 = operand1.associateWith { UUID.randomUUID().toString() }

        val inputs = inputKeys.mapIndexed { index, key ->
            key to setValue(operand1[index], operatorUUID1[operand1[index]]!!, context)
        }.toMap()
        visitors[identifier] = BlockSpec(
            opcode = opCode,
            inputs = inputs
        ).toBlock(nextUUID, parent)
        operatorUUID1.forEach { (t, u) ->
            t.visit(visitors, identifier, u, null, context)
        }
    }
}
