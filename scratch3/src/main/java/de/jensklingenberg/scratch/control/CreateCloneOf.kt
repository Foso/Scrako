package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.Sprite
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class CreateCloneOf(private val spriteName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_create_clone_of,
            inputs = mapOf("CLONE_OPTION" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid.toString()))))
        ).toBlock(nextUUID, parent)
        CreateCloneOfMenu(spriteName).visit(
            visitors,
            identifier.toString(),
            uuid,
            null,
            context,
        )
    }
}

private class CreateCloneOfMenu(private val spriteName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_create_clone_of_menu,
            fields = mapOf("CLONE_OPTION" to listOf(spriteName))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.createCloneOf(spriteName: String) = addNode(CreateCloneOf(spriteName))

fun ScriptBuilder.createCloneOf(sprite: Sprite) = createCloneOf(sprite.name)