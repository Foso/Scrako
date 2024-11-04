package de.jensklingenberg.scratch3.operator

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID
import kotlin.String
import kotlin.collections.MutableMap

private class LengthOf(
  public val string: ReporterBlock,
) : ReporterBlock {
  override fun visit(
    visitors: MutableMap<String, Block>,
    parent: String?,
    identifier: String,
    nextUUID: String?,
    context: Context,
  ) {
    val stringId = UUID.randomUUID().toString()
    visitors[identifier] = BlockSpec(
        opcode = "operator_length",
        inputs = mapOf("STRING" to setValue(string, stringId, context) ),
        fields = mapOf(),
        
    ).toBlock(nextUUID, identifier)
    string.visit(visitors, identifier, stringId, null, context)
  }
}

public fun lengthOf(string: ReporterBlock): ReporterBlock= LengthOf(string)
public fun lengthOf(string: String): ReporterBlock= LengthOf(StringBlock(string))