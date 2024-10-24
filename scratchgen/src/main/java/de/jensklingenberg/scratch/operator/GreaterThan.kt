package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock

class GreaterThan(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), OpCode.operator_gt
), ReporterBlock, BooleanBlock

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