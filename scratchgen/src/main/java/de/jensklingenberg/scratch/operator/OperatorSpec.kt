package de.jensklingenberg.scratch.operator

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import java.util.UUID

interface BooleanBlock : ReporterBlock

abstract class OperatorSpec(
    override val opcode: String, map: Map<String, JsonArray>
) : BlockSpec(
    opcode, inputs = map
), BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        super.visit(visitors, parent, index, identifier, null, layer, context)
    }
}

class OperatorAdd(operand1: Int, operand2: Int) : OperatorSpec(
    OpCode.operator_add, mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class OperatorEquals(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_equals", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class GreaterThan(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_gt", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class OperatorLessThan(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_lt", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class OperatorSubtract(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_subtract", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class Multiply(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_multiply", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class OperatorDivide(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_divide", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
)

class LetterOf(operand1: Int, operand2: String) : OperatorSpec(
    OpCode.operator_letter_of, mapOf(
        "LETTER" to createMessage(1, 6, operand1.toString()),
        "STRING" to createMessage(1, 10, operand2)
    )
)

fun createOperand(message: String) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(10),
                JsonPrimitive(message)
            )
        )
    )
)


fun createNum(message: String) = createMessage(1, 4, message)