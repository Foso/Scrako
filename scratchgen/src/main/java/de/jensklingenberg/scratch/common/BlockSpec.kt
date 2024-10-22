package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.model.Comment
import de.jensklingenberg.scratch.model.Mutation
import kotlinx.serialization.json.JsonArray
import java.util.UUID

open class BlockSpec(
    override val opcode: String,
    override val inputs: Map<String, JsonArray> = emptyMap(),
    override val fields: Map<String, List<String?>> = emptyMap(),
    override val shadow: Boolean = false,
    override val x: Int? = null,
    override val y: Int? = null,
    val mutation: Mutation? = null
) : CommonBlockSpec {

    open var comment: Comment? = null
    fun toBlock(next: String?, parent: String?, topLevel: Boolean, comment: String? = null) = Block(
        opcode = opcode,
        next = next,
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
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        comment?.addBlock(identifier.toString())
        visitors[identifier.toString()] =
            toBlock(nextUUID?.toString(), context.parent, context.topLevel, comment?.id)
    }

    fun addComment(comment: Comment): Node {
        this.comment = comment
        return this
    }
}