package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

interface BooleanBlock : ReporterBlock


abstract class Operator(
    private val operand1: ReporterBlock,
    private val operand2: ReporterBlock,
    private val inputName: List<String>,
    private val opCode: String
) : Node, ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID1 = UUID.randomUUID()
        val operatorUUID2 = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = opCode,
            inputs = mapOf(
                inputName[0] to setValue(operand1, operatorUUID1),
                inputName[1] to setValue(operand2, operatorUUID2)
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
        operand1.visit(visitors, identifier.toString(), operatorUUID1, null, context)
        operand2.visit(visitors, identifier.toString(), operatorUUID2, null, context)
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


fun createNum(message: String) = createMessage(1, 4, message)