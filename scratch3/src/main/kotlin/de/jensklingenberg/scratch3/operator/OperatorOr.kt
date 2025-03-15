package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock

class OperatorOr(operand1: BooleanBlock, operand2: BooleanBlock) :
    Operator(listOf(operand1, operand2), listOf("OPERAND1", "OPERAND2"), "operator_or"), BooleanBlock