package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class Multiply(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1,operand2),
    listOf("NUM1", "NUM2"),
    OpCode.operator_multiply
), ReporterBlock

