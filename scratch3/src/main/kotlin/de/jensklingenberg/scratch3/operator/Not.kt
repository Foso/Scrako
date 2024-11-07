package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock

class Not(operand1: BooleanBlock) :
    Operator(listOf(operand1), listOf("OPERAND"), "operator_not"), BooleanBlock

fun not(operand1: BooleanBlock): BooleanBlock = Not(operand1)
