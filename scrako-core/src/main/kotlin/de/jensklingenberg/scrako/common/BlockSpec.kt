package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Comment
import de.jensklingenberg.scrako.model.Mutation
import kotlinx.serialization.json.JsonArray

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
    fun toBlock(next: String?, parent: String?, comment: String? = null) = Block(
        opcode = opcode,
        next = next,
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
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        if (this is HatBlock && parent != null) {
            throw IllegalStateException("HatBlock blocks can't have a parent")
        }
        comment?.addBlock(identifier)
        visitors[identifier] =
            toBlock(nextUUID, parent, comment?.id)
    }

    fun addComment(comment: Comment): Node {
        this.comment = comment
        return this
    }
}