package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock

class Join(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("STRING1", "STRING2"), "operator_join")

fun join(operand1: ReporterBlock, operand2: ReporterBlock) = Join(operand1, operand2)
fun join(operand1: String, operand2: ReporterBlock) = Join(StringBlock(operand1), operand2)

fun join(operand1: String, operand2: String) = Join((StringBlock(operand1)), (StringBlock(operand2)))