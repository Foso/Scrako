package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.ArgumentType

data class ArgumentDataHolder internal constructor(val name: String, val id: String, val functionName: String, val type: ArgumentType)