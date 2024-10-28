package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

class Join(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("STRING1", "STRING2"), OpCode.operator_join)