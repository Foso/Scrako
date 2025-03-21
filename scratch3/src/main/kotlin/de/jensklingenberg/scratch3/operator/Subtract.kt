package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock


class Subtract(operand: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand, operand2),
    listOf("NUM1", "NUM2"),
    "operator_subtract"
), ReporterBlock


fun sub(operand1: Double, operand2: Double) = Subtract(DoubleBlock(operand1), DoubleBlock(operand2))
fun sub(operand1: Double, operand2: Int) = Subtract(DoubleBlock(operand1), IntBlock(operand2))
fun sub(operand1: Double, operand2: ReporterBlock) = Subtract(DoubleBlock(operand1), operand2)
fun sub(operand1: Double, operand2: String) = Subtract(DoubleBlock(operand1), StringBlock(operand2))

fun sub(operand1: Int, operand2: Double) = Subtract(IntBlock(operand1), DoubleBlock(operand2))
fun sub(operand1: Int, operand2: Int) = Subtract(IntBlock(operand1), IntBlock(operand2))
fun sub(operand1: Int, operand2: ReporterBlock) = Subtract(IntBlock(operand1), operand2)
fun sub(operand1: Int, operand2: String) = Subtract(IntBlock(operand1), StringBlock(operand2))

fun sub(operand1: ReporterBlock, operand2: Double) = Subtract(operand1, DoubleBlock(operand2))
fun sub(operand1: ReporterBlock, operand2: Int) = Subtract(operand1, IntBlock(operand2))
fun sub(operand1: ReporterBlock, operand2: ReporterBlock) = Subtract(operand1, operand2)
fun sub(operand1: ReporterBlock, operand2: String) = Subtract(operand1, StringBlock(operand2))

fun sub(operand1: String, operand2: String) = Subtract(StringBlock(operand1), StringBlock(operand2))
fun sub(operand1: String, operand2: Double) = Subtract(StringBlock(operand1), DoubleBlock(operand2))
fun sub(operand1: String, operand2: Int) = Subtract(StringBlock(operand1), IntBlock(operand2))
fun sub(operand1: String, operand2: ReporterBlock) = Subtract(StringBlock(operand1), operand2)