package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import java.util.UUID


fun createBlocks23(blockSpecs: List<List<Node>>): Map<String, Block> {

    return blockSpecs.map { createBlocks2(it) }.flatMap { it.toList() }.toMap()
}


private fun createBlocks2(blockSpecs: List<Node>): Map<String, Block> {
    val blockMap = mutableMapOf<String, Block>()

    val uuids = blockSpecs.map { UUID.randomUUID() }

    blockSpecs.forEachIndexed { index, blockSpec ->

        val parent = when {
            index == 0 -> null
            else -> uuids[index - 1].toString()
        }

        val nextNode = if (index != blockSpecs.lastIndex) uuids[index + 1] else null

        blockSpec.visit(blockMap, parent, uuids[index], nextNode, 0, Context(parent, topLevel = index == 0))

    }

    return blockMap
}