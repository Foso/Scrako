package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringBlock

class OperatorContains(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("STRING1", "STRING2"), OpCode.operator_contains), ReporterBlock,
    BooleanBlock

fun contains(word: ReporterBlock, contains: String) = OperatorContains(word, StringBlock(contains))

fun contains(word: String, contains: String) = OperatorContains(StringBlock(word), StringBlock(contains))