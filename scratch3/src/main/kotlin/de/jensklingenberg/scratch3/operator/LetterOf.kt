package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock

class LetterOf(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2),
    listOf("LETTER", "STRING"),
    "operator_letter_of"
), ReporterBlock