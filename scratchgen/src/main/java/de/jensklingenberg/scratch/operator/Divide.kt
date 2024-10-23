package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.motion.DoubleBlock
import de.jensklingenberg.scratch.motion.IntBlock

class Divide(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1,operand2),
    listOf("OPERAND1", "OPERAND2"),
    OpCode.operator_divide
), ReporterBlock


fun div(operand1: Double, operand2: Double) = Divide(DoubleBlock(operand1), DoubleBlock(operand2))
fun div(operand1: Double, operand2: Int) = Divide(DoubleBlock(operand1), IntBlock(operand2))
fun div(operand1: Double, operand2: ReporterBlock) = Divide(DoubleBlock(operand1), operand2)
fun div(operand1: Double, operand2: String) = Divide(DoubleBlock(operand1), StringReporter(operand2))

fun div(operand1: Int, operand2: Double) = Divide(IntBlock(operand1), DoubleBlock(operand2))
fun div(operand1: Int, operand2: Int) = Divide(IntBlock(operand1), IntBlock(operand2))
fun div(operand1: Int, operand2: ReporterBlock) = Divide(IntBlock(operand1), operand2)
fun div(operand1: Int, operand2: String) = Divide(IntBlock(operand1), StringReporter(operand2))

fun div(operand1: ReporterBlock, operand2: Double) = Divide(operand1, DoubleBlock(operand2))
fun div(operand1: ReporterBlock, operand2: Int) = Divide(operand1, IntBlock(operand2))
fun div(operand1: ReporterBlock, operand2: ReporterBlock) = Divide(operand1, operand2)
fun div(operand1: ReporterBlock, operand2: String) = Divide(operand1, StringReporter(operand2))

fun div(operand1: String, operand2: String) = Divide(StringReporter(operand1), StringReporter(operand2))
fun div(operand1: String, operand2: Double) = Divide(StringReporter(operand1), DoubleBlock(operand2))
fun div(operand1: String, operand2: Int) = Divide(StringReporter(operand1), IntBlock(operand2))
fun div(operand1: String, operand2: ReporterBlock) = Divide(StringReporter(operand1), operand2)