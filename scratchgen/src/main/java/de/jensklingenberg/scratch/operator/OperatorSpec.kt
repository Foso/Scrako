package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.motion.DoubleBlock
import de.jensklingenberg.scratch.motion.IntBlock
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

interface BooleanBlock : ReporterBlock

abstract class OperatorSpec(
    override val opcode: String,
    inputs: Map<String, JsonArray> = emptyMap(),
    fields: Map<String, List<String?>> = emptyMap()
) : BlockSpec(
    opcode, inputs = inputs, fields = fields
) {
}


open class Operator2(
    private val operand1: ReporterBlock,
    private val operand2: ReporterBlock,
    val inputName: List<String>,
    val s: String
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
            opcode = s,
            inputs = mapOf(
                inputName[0] to setValue(operand1, operatorUUID1),
                inputName[1] to setValue(operand2, operatorUUID2)
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
        operand1.visit(visitors, identifier.toString(), operatorUUID1, null, context)
        operand2.visit(visitors, identifier.toString(), operatorUUID2, null, context)
    }
}

class OperatorAdd(private val operand1: ReporterBlock, private val operand2: ReporterBlock) :
    Operator2(operand1, operand2, listOf("NUM1", "NUM2"), OpCode.operator_add), ReporterBlock


class OperatorContains(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator2(operand1, operand2, listOf("STRING1", "STRING2"), OpCode.operator_contains), ReporterBlock


class PickRandom(from: Int, to: Int) : OperatorSpec(
    OpCode.operator_random, mapOf(
        "FROM" to createNum(from.toString()),
        "TO" to createNum(to.toString())
    )
), ReporterBlock

class OperatorEquals(operand1: Int, operand2: Int) : OperatorSpec(
    "operator_equals", mapOf(
        "OPERAND1" to createNum(operand1.toString()),
        "OPERAND2" to createNum(operand2.toString())
    )
), BooleanBlock

class GreaterThan(private val operand1: ReporterBlock, private val operand2: ReporterBlock) : Operator2(operand1, operand2, listOf("OPERAND1", "OPERAND2"), OpCode.operator_gt), ReporterBlock, BooleanBlock


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
) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        super.visit(visitors, parent, identifier, nextUUID, context)
    }
}

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


class OperatorMathOps(operand1: String, operand2: Int) : OperatorSpec(
    OpCode.operator_mathop, inputs = mapOf(
        "NUM" to createMessage(1, 4, operand1),
        "OPERATOR" to createNum(operand2.toString())
    )
)

fun contains(word: ReporterBlock, contains: String) = OperatorContains(word, StringReporter(contains))

fun contains(word: String, contains: String) = OperatorContains(StringReporter(word), StringReporter(contains))
fun lt(operand1: Int, operand2: Int) = LessThan(operand1, operand2)

fun gt(operand1: Double, operand2: Double) = GreaterThan(DoubleBlock(operand1), DoubleBlock(operand2))
fun gt(operand1: Double, operand2: Int) = GreaterThan(DoubleBlock(operand1), IntBlock(operand2))
fun gt(operand1: Double, operand2: ReporterBlock) = GreaterThan(DoubleBlock(operand1), operand2)
fun gt(operand1: Double, operand2: String) = GreaterThan(DoubleBlock(operand1), StringReporter(operand2))

fun gt(operand1: Int, operand2: Double) = GreaterThan(IntBlock(operand1), DoubleBlock(operand2))
fun gt(operand1: Int, operand2: Int) = GreaterThan(IntBlock(operand1), IntBlock(operand2))
fun gt(operand1: Int, operand2: ReporterBlock) = GreaterThan(IntBlock(operand1), operand2)
fun gt(operand1: Int, operand2: String) = GreaterThan(IntBlock(operand1), StringReporter(operand2))

fun gt(operand1: ReporterBlock, operand2: Double) = GreaterThan(operand1, DoubleBlock(operand2))
fun gt(operand1: ReporterBlock, operand2: Int) = GreaterThan(operand1, IntBlock(operand2))
fun gt(operand1: ReporterBlock, operand2: ReporterBlock) = GreaterThan(operand1, operand2)
fun gt(operand1: ReporterBlock, operand2: String) = GreaterThan(operand1, StringReporter(operand2))

fun gt(operand1: String, operand2: String) = GreaterThan(StringReporter(operand1), StringReporter(operand2))
fun gt(operand1: String, operand2: Double) = GreaterThan(StringReporter(operand1), DoubleBlock(operand2))
fun gt(operand1: String, operand2: Int) = GreaterThan(StringReporter(operand1), IntBlock(operand2))
fun gt(operand1: String, operand2: ReporterBlock) = GreaterThan(StringReporter(operand1), operand2)

fun add(operand1: Double, operand2: Double) = OperatorAdd(DoubleBlock(operand1), DoubleBlock(operand2))
fun add(operand1: Double, operand2: Int) = OperatorAdd(DoubleBlock(operand1), IntBlock(operand2))
fun add(operand1: Double, operand2: ReporterBlock) = OperatorAdd(DoubleBlock(operand1), operand2)
fun add(operand1: Double, operand2: String) = OperatorAdd(DoubleBlock(operand1), StringReporter(operand2))

fun add(operand1: Int, operand2: Double) = OperatorAdd(IntBlock(operand1), DoubleBlock(operand2))
fun add(operand1: Int, operand2: Int) = OperatorAdd(IntBlock(operand1), IntBlock(operand2))
fun add(operand1: Int, operand2: ReporterBlock) = OperatorAdd(IntBlock(operand1), operand2)
fun add(operand1: Int, operand2: String) = OperatorAdd(IntBlock(operand1), StringReporter(operand2))

fun add(operand1: ReporterBlock, operand2: Double) = OperatorAdd(operand1, DoubleBlock(operand2))
fun add(operand1: ReporterBlock, operand2: Int) = OperatorAdd(operand1, IntBlock(operand2))
fun add(operand1: ReporterBlock, operand2: ReporterBlock) = OperatorAdd(operand1, operand2)
fun add(operand1: ReporterBlock, operand2: String) = OperatorAdd(operand1, StringReporter(operand2))

fun add(operand1: String, operand2: String) = OperatorAdd(StringReporter(operand1), StringReporter(operand2))
fun add(operand1: String, operand2: Double) = OperatorAdd(StringReporter(operand1), DoubleBlock(operand2))
fun add(operand1: String, operand2: Int) = OperatorAdd(StringReporter(operand1), IntBlock(operand2))
fun add(operand1: String, operand2: ReporterBlock) = OperatorAdd(StringReporter(operand1), operand2)
fun createNum(message: String) = createMessage(1, 4, message)