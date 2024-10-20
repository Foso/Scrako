package me.jens

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.BlockSpecSpec
import scratch.Block
import me.jens.scratch.OpCode

abstract class OperatorSpec(private val operand1: Int, private val operand2: Int, override val opcode: String) : BlockSpecSpec(
    opcode, inputs = mapOf(
        "NUM1" to createNum(operand1.toString()),
        "NUM2" to createNum(operand2.toString())
    )
){
    override fun visit(
        visitors: MutableMap<String, Block>,
        layer: Int,
        parent: String?,
        index: Int,
        next: Boolean,
        listIndex: Int
    ) {
        super.visit(visitors, layer, parent, index, false,listIndex)
    }
}

class OperatorAdd(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, OpCode.operator_add)
class OperatorEquals(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_equals")
class OperatorGreaterThan(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_gt")
class OperatorLessThan(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_lt")
class OperatorSubtract(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_subtract")
class OperatorMultiply(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_multiply")
class OperatorDivide(operand1: Int, operand2: Int) : OperatorSpec(operand1, operand2, "operator_divide")

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


fun createNum(message: String) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(4),
                JsonPrimitive(message)
            )
        )
    )
)