package de.jensklingenberg.scratch3.looks

import BackdropOptions
import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import looks_backdrops
import java.util.UUID

private class SwitchBackdropAndWait(val block0: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_switchbackdroptoandwait",
            inputs = mapOf("BACKDROP" to setValue(block0, block0Id, context))
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}


fun CommonScriptBuilder.switchBackdropAndWait(block0: BackdropOptions) = addNode(SwitchBackdropAndWait(looks_backdrops(block0.s)))
fun CommonScriptBuilder.switchBackdropAndWait(block0: String) = addNode(SwitchBackdropAndWait(looks_backdrops(block0)))