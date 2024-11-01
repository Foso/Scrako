package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

class Multiply(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2),
    listOf("NUM1", "NUM2"),
    OpCode.operator_multiply
), ReporterBlock

