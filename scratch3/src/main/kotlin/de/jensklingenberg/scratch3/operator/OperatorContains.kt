package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock

class OperatorContains(operand1: ReporterBlock, operand2: ReporterBlock) :
    Operator(listOf(operand1, operand2), listOf("STRING1", "STRING2"), "operator_contains"), ReporterBlock,
    BooleanBlock

fun contains(word: ReporterBlock, contains: String): BooleanBlock  = OperatorContains(word, StringBlock(contains))

fun contains(word: String, contains: String): BooleanBlock  = OperatorContains(StringBlock(word), StringBlock(contains))

fun contains(word: ReporterBlock, contains: ReporterBlock): BooleanBlock  = OperatorContains(word, contains)

fun ReporterBlock.containsText(block: ReporterBlock) : BooleanBlock = OperatorContains(this, block)
