package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID


abstract class Operator(
    private val operand1: List<ReporterBlock>,
    private val inputKeys: List<String>,
    private val opCode: String
) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

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

    infix operator fun plus(add: ReporterBlock): Add {
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


