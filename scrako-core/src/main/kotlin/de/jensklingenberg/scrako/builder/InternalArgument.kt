package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.ArgumentType

data class InternalArgument internal constructor(val name: String, val id: String, val functionName: String, val type: ArgumentType)