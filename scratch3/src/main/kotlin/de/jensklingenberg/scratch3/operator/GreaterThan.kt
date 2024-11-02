package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock

class GreaterThan(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), "operator_gt"
), BooleanBlock

fun gt(operand1: Double, operand2: Double) = GreaterThan(DoubleBlock(operand1), DoubleBlock(operand2))
fun gt(operand1: Double, operand2: Int) = GreaterThan(DoubleBlock(operand1), IntBlock(operand2))
fun gt(operand1: Double, operand2: ReporterBlock) = GreaterThan(DoubleBlock(operand1), operand2)
fun gt(operand1: Double, operand2: String) = GreaterThan(DoubleBlock(operand1), StringBlock(operand2))

fun gt(operand1: Int, operand2: Double) = GreaterThan(IntBlock(operand1), DoubleBlock(operand2))
fun gt(operand1: Int, operand2: Int) = GreaterThan(IntBlock(operand1), IntBlock(operand2))
fun gt(operand1: Int, operand2: ReporterBlock) = GreaterThan(IntBlock(operand1), operand2)
fun gt(operand1: Int, operand2: String) = GreaterThan(IntBlock(operand1), StringBlock(operand2))

fun gt(operand1: ReporterBlock, operand2: Double) = GreaterThan(operand1, DoubleBlock(operand2))
fun gt(operand1: ReporterBlock, operand2: Int) = GreaterThan(operand1, IntBlock(operand2))
fun gt(operand1: ReporterBlock, operand2: ReporterBlock) = GreaterThan(operand1, operand2)
fun gt(operand1: ReporterBlock, operand2: String) = GreaterThan(operand1, StringBlock(operand2))

fun gt(operand1: String, operand2: String) = GreaterThan(StringBlock(operand1), StringBlock(operand2))
fun gt(operand1: String, operand2: Double) = GreaterThan(StringBlock(operand1), DoubleBlock(operand2))
fun gt(operand1: String, operand2: Int) = GreaterThan(StringBlock(operand1), IntBlock(operand2))
fun gt(operand1: String, operand2: ReporterBlock) = GreaterThan(StringBlock(operand1), operand2)