package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringReporter
import de.jensklingenberg.scratch.motion.DoubleBlock
import de.jensklingenberg.scratch.motion.IntBlock

class LessThan(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), OpCode.operator_lt), BooleanBlock

fun lt(operand1: Double, operand2: Double) = LessThan(DoubleBlock(operand1), DoubleBlock(operand2))
fun lt(operand1: Double, operand2: Int) = LessThan(DoubleBlock(operand1), IntBlock(operand2))
fun lt(operand1: Double, operand2: ReporterBlock) = LessThan(DoubleBlock(operand1), operand2)
fun lt(operand1: Double, operand2: String) = LessThan(DoubleBlock(operand1), StringReporter(operand2))

fun lt(operand1: Int, operand2: Double) = LessThan(IntBlock(operand1), DoubleBlock(operand2))
fun lt(operand1: Int, operand2: Int) = LessThan(IntBlock(operand1), IntBlock(operand2))
fun lt(operand1: Int, operand2: ReporterBlock) = LessThan(IntBlock(operand1), operand2)
fun lt(operand1: Int, operand2: String) = LessThan(IntBlock(operand1), StringReporter(operand2))

fun lt(operand1: ReporterBlock, operand2: Double) = LessThan(operand1, DoubleBlock(operand2))
fun lt(operand1: ReporterBlock, operand2: Int) = LessThan(operand1, IntBlock(operand2))
fun lt(operand1: ReporterBlock, operand2: ReporterBlock) = LessThan(operand1, operand2)
fun lt(operand1: ReporterBlock, operand2: String) = LessThan(operand1, StringReporter(operand2))

fun lt(operand1: String, operand2: String) = LessThan(StringReporter(operand1), StringReporter(operand2))
fun lt(operand1: String, operand2: Double) = LessThan(StringReporter(operand1), DoubleBlock(operand2))
fun lt(operand1: String, operand2: Int) = LessThan(StringReporter(operand1), IntBlock(operand2))
fun lt(operand1: String, operand2: ReporterBlock) = LessThan(StringReporter(operand1), operand2)