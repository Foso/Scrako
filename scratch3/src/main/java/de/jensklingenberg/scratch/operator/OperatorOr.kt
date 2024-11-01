package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock

class OperatorOr(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), "operator_or"), BooleanBlock