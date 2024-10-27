package de.jensklingenberg.scrako.common

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
    fun toBlock(next: UUID?, parent: String?, comment: String? = null) = Block(
        opcode = opcode,
        next = next?.toString(),
        parent = parent,
        inputs = inputs,
        fields = fields,
        shadow = shadow,
        topLevel = parent == null,
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
        if (this is HatBlock && parent != null) {
            throw IllegalStateException("HatBlock blocks can't have a parent")
        }
        comment?.addBlock(identifier.toString())
        visitors[identifier.toString()] =
            toBlock(nextUUID, parent, comment?.id)
    }

    fun addComment(comment: Comment): Node {
        this.comment = comment
        return this
    }
}