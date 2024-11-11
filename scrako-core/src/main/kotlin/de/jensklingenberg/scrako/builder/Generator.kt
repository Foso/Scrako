package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID


fun createBlocks23(blockSpecs: List<List<Node>>, context: Context): Map<String, BlockFull> {
    return blockSpecs.map {

        val blockFullMap = mutableMapOf<String, BlockFull>()
        val uuids = it.map { UUID.randomUUID().toString() }
        it.forEachIndexed { index, blockSpec ->

            val parent = when {
                index == 0 -> null
                else -> uuids[index - 1]
            }

            val nextNode = if (index != it.lastIndex) uuids[index + 1] else null

            blockSpec.visit(blockFullMap, parent, uuids[index], nextNode, context)

        }
        blockFullMap
    }.flatMap { it.toList() }.toMap()
}


