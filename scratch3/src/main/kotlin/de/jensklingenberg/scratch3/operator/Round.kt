package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock

class Round(operand1: ReporterBlock) :
    Operator(listOf(operand1), listOf("NUM"), "operator_round")