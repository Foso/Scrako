package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock

private class PickRandom(from: ReporterBlock, to: ReporterBlock) : Operator(
    listOf(from, to),
    listOf("FROM", "TO"),
    "operator_random"
), ReporterBlock

fun random(from: Double, to: Double): ReporterBlock = PickRandom(DoubleBlock(from), DoubleBlock(to))
fun random(from: Double, to: ReporterBlock): ReporterBlock = PickRandom(DoubleBlock(from), to)
fun random(from: Double, to: Int): ReporterBlock = PickRandom(DoubleBlock(from), IntBlock(to))

fun random(from: ReporterBlock, to: Double): ReporterBlock = PickRandom(from, DoubleBlock(to))
fun random(from: ReporterBlock, to: ReporterBlock): ReporterBlock = PickRandom(from, to)
fun random(from: ReporterBlock, to: Int): ReporterBlock = PickRandom(from, IntBlock(to))

fun random(from: Int, to: Int): ReporterBlock = PickRandom(IntBlock(from), IntBlock(to))
fun random(from: Int, to: Double): ReporterBlock = PickRandom(IntBlock(from), DoubleBlock(to))
fun random(from: Int, to: ReporterBlock): ReporterBlock = PickRandom(IntBlock(from), to)





