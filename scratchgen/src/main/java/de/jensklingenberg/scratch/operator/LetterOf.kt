package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class LetterOf(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2),
    listOf("LETTER", "STRING"),
    OpCode.operator_letter_of
), ReporterBlock