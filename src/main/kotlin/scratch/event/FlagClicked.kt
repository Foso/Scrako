package me.jens.scratch.event

import me.jens.Event
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

class FlagClicked(x: Int = 0, y: Int =0) : BlockSpecSpec(
   opcode = OpCode.Whenflagclicked,
   x = x,
   y = y
), Event

