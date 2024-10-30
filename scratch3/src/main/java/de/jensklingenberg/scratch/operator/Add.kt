package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.common.OpCode

class Add(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("NUM1", "NUM2"), OpCode.operator_add), ReporterBlock


fun add(operand1: Double, operand2: Double) = Add(DoubleBlock(operand1), DoubleBlock(operand2))
fun add(operand1: Double, operand2: Int) = Add(DoubleBlock(operand1), IntBlock(operand2))
fun add(operand1: Double, operand2: ReporterBlock) = Add(DoubleBlock(operand1), operand2)
fun add(operand1: Double, operand2: String) = Add(DoubleBlock(operand1), StringBlock(operand2))

fun add(operand1: Int, operand2: Double) = Add(IntBlock(operand1), DoubleBlock(operand2))
fun add(operand1: Int, operand2: Int) = Add(IntBlock(operand1), IntBlock(operand2))
fun add(operand1: Int, operand2: ReporterBlock) = Add(IntBlock(operand1), operand2)
fun add(operand1: Int, operand2: String) = Add(IntBlock(operand1), StringBlock(operand2))

fun add(operand1: ReporterBlock, operand2: Double) = Add(operand1, DoubleBlock(operand2))
fun add(operand1: ReporterBlock, operand2: Int) = Add(operand1, IntBlock(operand2))
fun add(operand1: ReporterBlock, operand2: ReporterBlock) = Add(operand1, operand2)
fun add(operand1: ReporterBlock, operand2: String) = Add(operand1, StringBlock(operand2))

fun add(operand1: String, operand2: String) = Add(StringBlock(operand1), StringBlock(operand2))
fun add(operand1: String, operand2: Double) = Add(StringBlock(operand1), DoubleBlock(operand2))
fun add(operand1: String, operand2: Int) = Add(StringBlock(operand1), IntBlock(operand2))
fun add(operand1: String, operand2: ReporterBlock) = Add(StringBlock(operand1), operand2)

infix fun BooleanBlock.and(booleanBlock: BooleanBlock): BooleanBlock {
    return And(this, booleanBlock)
}

infix fun BooleanBlock.or(booleanBlock: BooleanBlock): BooleanBlock {
    return OperatorOr(this, booleanBlock)
}

infix fun ReporterBlock.eq(booleanBlock: ReporterBlock): BooleanBlock {
    return OperatorEquals(this, booleanBlock)
}

operator fun BooleanBlock.not(): BooleanBlock {
    return Not(this)
}

infix fun ReporterBlock.lt(reporterBlock: ReporterBlock): BooleanBlock {
    return LessThan(this, reporterBlock)
}

infix fun ReporterBlock.gt(reporterBlock: ReporterBlock): BooleanBlock {
    return GreaterThan(this, reporterBlock)
}

infix fun ReporterBlock.gt(reporterBlock: Int): BooleanBlock {
    return GreaterThan(this, IntBlock(reporterBlock))
}

operator fun ReporterBlock.minus(booleanBlock: ReporterBlock): ReporterBlock {
    return Subtract(this, booleanBlock)
}



