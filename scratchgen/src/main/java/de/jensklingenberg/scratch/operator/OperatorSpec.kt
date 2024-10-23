package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

interface BooleanBlock : ReporterBlock

abstract class OperatorSpec(
    override val opcode: String, inputs: Map<String, JsonArray> = emptyMap(), fields : Map<String,List<String?>> =  emptyMap()
) : BlockSpec(
    opcode, inputs = inputs, fields = fields
) {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        super.visit(visitors, parent, identifier, null, context)
    }


}



class OperatorMathOps(operand1: String, operand2: Int) : OperatorSpec(
    OpCode.operator_mathop, inputs = mapOf(
        "NUM" to createMessage(1,4,operand1),
        "OPERATOR" to createNum(operand2.toString())
    )
)

class OperatorAdd(operand1: Int, operand2: Int) : OperatorSpec(
    OpCode.operator_add, inputs = mapOf(
        "NUM1" to createNum(operand1.toString()),
        "NUM2" to createNum(operand2.toString())
    )
), ReporterBlock

class OperatorContains(operand1: String, operand2: String) : OperatorSpec(
    OpCode.operator_contains, mapOf(
        "STRING1" to createNum(operand1),
        "STRING2" to createNum(operand2)
    )
), ReporterBlock


class PickRandom(from: Int, to: Int) : OperatorSpec(
    OpCode.operator_random, mapOf(
        "FROM" to createNum(from.toString()),
        "TO" to createNum(to.toString())
    )
)

class OperatorEquals(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_equals", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
), BooleanBlock

class GreaterThan(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_gt", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
), BooleanBlock

class LessThan(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_lt", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
), BooleanBlock

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
fun contains(operand1: String, operand2: String) = OperatorContains(operand1, operand2)
fun lt(operand1: Int, operand2: Int) = LessThan(operand1, operand2)
fun gt(operand1: Int, operand2: Int) = GreaterThan(operand1, operand2)
fun add(operand1: Int, operand2: Int) = OperatorAdd(operand1, operand2)
fun createNum(message: String) = createMessage(1, 4, message)