package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.ReporterBlock

private class LetterOf(operand1: ReporterBlock, operand2: ReporterBlock) : Operator(
    listOf(operand1, operand2),
    listOf("LETTER", "STRING"),
    "operator_letter_of"
), ReporterBlock

fun letterOf(letter: ReporterBlock, word: ReporterBlock) : Operator = LetterOf(letter, word)