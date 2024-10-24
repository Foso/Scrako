package de.jensklingenberg.scratch.operator

import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock

class PickRandom(from: ReporterBlock, to: ReporterBlock) : Operator(
    listOf(from),
    listOf("FROM", "TO"),
    OpCode.operator_random
), ReporterBlock

fun random(from: Double, to: Double) = PickRandom(DoubleBlock(from), DoubleBlock(to))
fun random(from: Double, to: ReporterBlock) = PickRandom(DoubleBlock(from), to)
fun random(from: Double, to: Int) = PickRandom(DoubleBlock(from), IntBlock(to))

fun random(from: ReporterBlock, to: Double) = PickRandom(from, DoubleBlock(to))
fun random(from: ReporterBlock, to: ReporterBlock) = PickRandom(from, to)
fun random(from: ReporterBlock, to: Int) = PickRandom(from, IntBlock(to))

fun random(from: Int, to: Int) = PickRandom(IntBlock(from), IntBlock(to))
fun random(from: Int, to: Double) = PickRandom(IntBlock(from), DoubleBlock(to))
fun random(from: Int, to: ReporterBlock) = PickRandom(IntBlock(from), to)





