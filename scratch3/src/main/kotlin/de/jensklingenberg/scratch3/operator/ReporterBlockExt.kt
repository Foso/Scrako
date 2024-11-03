package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock


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

fun ReporterBlock.minus(booleanBlock: ReporterBlock): ReporterBlock {
    return Subtract(this, booleanBlock)
}

infix fun ReporterBlock.times(intBlock: ReporterBlock): Multiply {
    return Multiply(this, intBlock)
}

infix fun ReporterBlock.div(width: ReporterBlock): Operator {
    return Divide(this, width)
}

infix fun ReporterBlock.or(booleanBlock: ReporterBlock): BooleanBlock {
    return OperatorOr(this, booleanBlock)
}

infix fun ReporterBlock.plus(i: Int): ReporterBlock {
    return Add(this, IntBlock(i))
}

infix fun ReporterBlock.plus(i: ReporterBlock): ReporterBlock {
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

infix fun ReporterBlock.div(i: Int): Operator {
    return Divide(this, IntBlock(i))
}

infix fun Int.gt(i: Int): BooleanBlock {
    return GreaterThan(IntBlock(this), IntBlock(i))
}

infix fun Int.eq(i: Int): BooleanBlock {
    return OperatorEquals(IntBlock(this), IntBlock(i))
}