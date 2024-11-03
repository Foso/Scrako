package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock

private class Round(operand1: ReporterBlock) :
    Operator(listOf(operand1), listOf("NUM"), "operator_round")

fun round(operand1: ReporterBlock): ReporterBlock = Round(operand1)