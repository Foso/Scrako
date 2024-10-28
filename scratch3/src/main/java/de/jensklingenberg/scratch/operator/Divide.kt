package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.common.OpCode

class Divide(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2),
    listOf("OPERAND1", "OPERAND2"),
    OpCode.operator_divide
), ReporterBlock


fun div(operand1: Double, operand2: Double) = Divide(DoubleBlock(operand1), DoubleBlock(operand2))
fun div(operand1: Double, operand2: Int) = Divide(DoubleBlock(operand1), IntBlock(operand2))
fun div(operand1: Double, operand2: ReporterBlock) = Divide(DoubleBlock(operand1), operand2)
fun div(operand1: Double, operand2: String) = Divide(DoubleBlock(operand1), StringBlock(operand2))

fun div(operand1: Int, operand2: Double) = Divide(IntBlock(operand1), DoubleBlock(operand2))
fun div(operand1: Int, operand2: Int) = Divide(IntBlock(operand1), IntBlock(operand2))
fun div(operand1: Int, operand2: ReporterBlock) = Divide(IntBlock(operand1), operand2)
fun div(operand1: Int, operand2: String) = Divide(IntBlock(operand1), StringBlock(operand2))

fun div(operand1: ReporterBlock, operand2: Double) = Divide(operand1, DoubleBlock(operand2))
fun div(operand1: ReporterBlock, operand2: Int) = Divide(operand1, IntBlock(operand2))
fun div(operand1: ReporterBlock, operand2: ReporterBlock) = Divide(operand1, operand2)
fun div(operand1: ReporterBlock, operand2: String) = Divide(operand1, StringBlock(operand2))

fun div(operand1: String, operand2: String) = Divide(StringBlock(operand1), StringBlock(operand2))
fun div(operand1: String, operand2: Double) = Divide(StringBlock(operand1), DoubleBlock(operand2))
fun div(operand1: String, operand2: Int) = Divide(StringBlock(operand1), IntBlock(operand2))
fun div(operand1: String, operand2: ReporterBlock) = Divide(StringBlock(operand1), operand2)