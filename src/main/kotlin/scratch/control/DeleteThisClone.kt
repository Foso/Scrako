package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode
import scratch.Block

class DeleteThisClone() : BlockSpecSpec(
   opcode = OpCode.control_delete_this_clone,
){
   override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
      super.visit(visitors, layer, parent, index, null)
   }
}