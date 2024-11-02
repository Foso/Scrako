package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock

class Add(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("NUM1", "NUM2"), "operator_add"), ReporterBlock


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

infix operator fun ReporterBlock.times(intBlock: ReporterBlock): Multiply {
    return Multiply(this, intBlock)
}

infix operator fun ReporterBlock.div(width: ReporterBlock): Operator {
    return Divide(this, width)
}

infix fun ReporterBlock.or(booleanBlock: ReporterBlock): BooleanBlock {
    return OperatorOr(this, booleanBlock)
}

infix fun ReporterBlock.plus(i: Int): ReporterBlock {
    return Add(this, IntBlock(i))
}

infix operator fun ReporterBlock.plus(i: ReporterBlock): ReporterBlock {
    return Add(this, i)
}

infix fun ReporterBlock.minus(i: Int): ReporterBlock {
    return Subtract(this, IntBlock(i))
}

infix fun ReporterBlock.times(i: Int): ReporterBlock {
    return Multiply(this, IntBlock(i))
}

infix fun Int.minus(intBlock: IntBlock): ReporterBlock {
    return Subtract(IntBlock(this), intBlock)
}

infix fun Int.plus(int: ReporterBlock): ReporterBlock {
    return Add(IntBlock(this), int)
}