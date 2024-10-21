package me.jens.scratch.common

import me.jens.scratch.Block
import java.util.UUID


fun createBlocks23(blockSpecs: List<List<Node>>): Map<String, Block> {
    val test: Map<String, Block> =
        blockSpecs.map { createBlocks2(it) }.flatMap { it.toList() }.toMap()

    return test
}


fun createBlocks2(blockSpecs: List<Node>): Map<String, Block> {
    val blockMap = mutableMapOf<String, Block>()

    val uuids = blockSpecs.map { UUID.randomUUID() }

    blockSpecs.forEachIndexed { index, blockSpec ->

        val parent = when {
            index == 0 -> null
            else -> uuids[index - 1].toString()
        }

        val nextNode = if (index != blockSpecs.lastIndex) uuids[index + 1] else null

        blockSpec.visit(blockMap, parent, index, uuids[index], nextNode, 0,Context(parent))

    }

    return blockMap
}