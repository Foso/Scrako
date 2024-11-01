package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

class Round(operand1: ReporterBlock) :
    Operator(listOf(operand1), listOf("NUM"), OpCode.operator_round)