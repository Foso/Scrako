package scratch.looks

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

class Show() : BlockSpecSpec(
   opcode = OpCode.looks_show,
)

class Hide() : BlockSpecSpec(
   opcode = OpCode.looks_hide,
)