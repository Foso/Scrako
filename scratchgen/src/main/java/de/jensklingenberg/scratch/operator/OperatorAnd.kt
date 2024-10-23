package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class OperatorAnd(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1,operand2), listOf("OPERAND1", "OPERAND2"), OpCode.operator_and), BooleanBlock

class OperatorNot(operand1: BooleanBlock) :
    Operator(listOf(operand1),  listOf("OPERAND"), OpCode.operator_not), BooleanBlock