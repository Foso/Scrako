package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.event.Event
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.model.Comment
import de.jensklingenberg.scratch.model.Mutation
import kotlinx.serialization.json.JsonArray
import java.util.UUID

open class BlockSpec(
     val opcode: String,
     val inputs: Map<String, JsonArray> = emptyMap(),
     val fields: Map<String, List<String?>> = emptyMap(),
     val shadow: Boolean = false,
     val x: Int? = null,
     val y: Int? = null,
    val mutation: Mutation? = null
) : Node {

    open var comment: Comment? = null
    fun toBlock(next: UUID?, parent: String?, topLevel: Boolean, comment: String? = null) = Block(
        opcode = opcode,
        next = next?.toString(),
        parent = parent,
        inputs = inputs,
        fields = fields,
        shadow = shadow,
        topLevel = topLevel,
        x = x,
        y = y,
        comment = comment,
        mutation = mutation
    )

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        if (this is Event && parent != null) {
            throw IllegalStateException("Event blocks can't have a parent")
        }
        comment?.addBlock(identifier.toString())
        visitors[identifier.toString()] =
            toBlock(nextUUID, context.parent, context.topLevel, comment?.id)
    }

    fun addComment(comment: Comment): Node {
        this.comment = comment
        return this
    }
}