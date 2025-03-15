package de.jensklingenberg.scratch3.control


import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CapBlock

internal class DeleteThisClone : BlockSpec(
    opcode = "control_delete_this_clone",
), CapBlock

fun SpriteScriptBuilder.deleteThisClone() = addNode(DeleteThisClone())