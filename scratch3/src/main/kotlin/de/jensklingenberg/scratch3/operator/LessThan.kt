package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.common.OpCode

class LessThan(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), OpCode.operator_lt), BooleanBlock

fun lt(operand1: Double, operand2: Double) = LessThan(DoubleBlock(operand1), DoubleBlock(operand2))
fun lt(operand1: Double, operand2: Int) = LessThan(DoubleBlock(operand1), IntBlock(operand2))
fun lt(operand1: Double, operand2: ReporterBlock) = LessThan(DoubleBlock(operand1), operand2)
fun lt(operand1: Double, operand2: String) = LessThan(DoubleBlock(operand1), StringBlock(operand2))

fun lt(operand1: Int, operand2: Double) = LessThan(IntBlock(operand1), DoubleBlock(operand2))
fun lt(operand1: Int, operand2: Int) = LessThan(IntBlock(operand1), IntBlock(operand2))
fun lt(operand1: Int, operand2: ReporterBlock) = LessThan(IntBlock(operand1), operand2)
fun lt(operand1: Int, operand2: String) = LessThan(IntBlock(operand1), StringBlock(operand2))

fun lt(operand1: ReporterBlock, operand2: Double) = LessThan(operand1, DoubleBlock(operand2))
fun lt(operand1: ReporterBlock, operand2: Int) = LessThan(operand1, IntBlock(operand2))
fun lt(operand1: ReporterBlock, operand2: ReporterBlock): BooleanBlock = LessThan(operand1, operand2)
fun lt(operand1: ReporterBlock, operand2: String) = LessThan(operand1, StringBlock(operand2))

fun lt(operand1: String, operand2: String) = LessThan(StringBlock(operand1), StringBlock(operand2))
fun lt(operand1: String, operand2: Double) = LessThan(StringBlock(operand1), DoubleBlock(operand2))
fun lt(operand1: String, operand2: Int) = LessThan(StringBlock(operand1), IntBlock(operand2))
fun lt(operand1: String, operand2: ReporterBlock) = LessThan(StringBlock(operand1), operand2)