package me.jens.scratch

import kotlinx.serialization.json.JsonArray
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import scratch.Comment
import java.util.UUID

open class BlockSpecSpec(
    override val opcode: String,
    override val inputs: Map<String, JsonArray> = emptyMap(),
    override val fields: Map<String, List<String?>> = emptyMap(),
    override val shadow: Boolean = false,
    override val x: Int? = null,
    override val y: Int? = null,
) : CommonBlockSpec {

    open var comment: Comment?=null
    fun toBlock(next: String?, parent: String?, topLevel: Boolean, comment: String?=null) = Block(
        opcode = opcode,
        next = next,
        parent = parent,
        inputs = inputs,
        fields = fields,
        shadow = shadow,
        topLevel = topLevel,
        x = x,
        y = y,
        comment = comment
    )

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        comment?.addBlock(name.toString())
        visitors[name.toString()] = toBlock(nextUUID?.toString(), context.parent, layer == 0 && index == 0, comment?.id)
    }

    fun addComment(comment: Comment): Node {
        this.comment = comment
        return this
    }
}