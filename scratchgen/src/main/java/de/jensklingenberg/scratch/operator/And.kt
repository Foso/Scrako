package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock

class And(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), OpCode.operator_and), BooleanBlock


