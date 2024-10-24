package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode

class Not(operand1: BooleanBlock) :
    Operator(listOf(operand1), listOf("OPERAND"), OpCode.operator_not), BooleanBlock