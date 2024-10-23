package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class OperatorEquals(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1,operand2), listOf("OPERAND1", "OPERAND1"), OpCode.operator_equals), BooleanBlock

