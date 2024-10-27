package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID


abstract class Operator(
    private val operand1: List<ReporterBlock>,
    private val inputKeys: List<String>,
    private val opCode: String
) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID1 = operand1.associateWith { UUID.randomUUID() }

        val inputs = inputKeys.mapIndexed { index, key ->
            key to setValue(operand1[index], operatorUUID1[operand1[index]]!!)
        }.toMap()
        visitors[identifier.toString()] = BlockSpec(
            opcode = opCode,
            inputs = inputs
        ).toBlock(nextUUID, parent)
        operatorUUID1.forEach { (t, u) ->
            t.visit(visitors, identifier.toString(), u, null, context)
        }
        // operand2?.visit(visitors, identifier.toString(), operatorUUID2, null, context)
    }

    operator fun plus(add: Add): Add {
        return Add(this, add)
    }

    operator fun times(add: Add): Multiply {
        return Multiply(this, add)
    }

    operator fun div(add: Add): Divide {
        return Divide(this, add)
    }

    operator fun minus(add: Add): Subtract {
        return Subtract(this, add)
    }
}


