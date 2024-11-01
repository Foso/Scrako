package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scratch3.common.OpCode

class Not(operand1: BooleanBlock) :
    Operator(listOf(operand1), listOf("OPERAND"), OpCode.operator_not), BooleanBlock

fun not(operand1: BooleanBlock): BooleanBlock = Not(operand1)
