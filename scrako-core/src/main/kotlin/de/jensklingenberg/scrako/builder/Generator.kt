package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block
import java.util.UUID


fun createBlocks23(blockSpecs: List<List<Node>>, context: Context): Map<String, Block> {
    return blockSpecs.map { createBlocks2(it, context) }.flatMap { it.toList() }.toMap()
}


private fun createBlocks2(blockSpecs: List<Node>, context: Context): Map<String, Block> {
    val blockMap = mutableMapOf<String, Block>()

    val uuids = blockSpecs.map { UUID.randomUUID().toString() }

    blockSpecs.forEachIndexed { index, blockSpec ->

        val parent = when {
            index == 0 -> null
            else -> uuids[index - 1].toString()
        }

        val nextNode = if (index != blockSpecs.lastIndex) uuids[index + 1] else null

        blockSpec.visit(blockMap, parent, uuids[index].toString(), nextNode, context)

    }

    return blockMap
}