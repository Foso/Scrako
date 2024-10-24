package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class CreateCloneOf(private val spriteName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_create_clone_of,
            inputs = mapOf("CLONE_OPTION" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid.toString()))))
        ).toBlock(nextUUID, parent, context.topLevel)
        CreateCloneOfMenu(spriteName).visit(
            visitors,
            identifier.toString(),
            uuid,
            null,
            context.copy(topLevel = false)
        )
    }
}

private class CreateCloneOfMenu(private val spriteName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_create_clone_of_menu,
            fields = mapOf("CLONE_OPTION" to listOf(spriteName))
        ).toBlock(nextUUID, parent, context.topLevel)
    }
}

fun NodeBuilder.createCloneOf(spriteName: String) = addChild(CreateCloneOf(spriteName))

fun NodeBuilder.createCloneOf(sprite: Sprite) = createCloneOf(sprite.name)