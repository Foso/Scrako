package de.jensklingenberg.scrako.common

sealed interface ArgumentType {
    data object BOOLEAN : ArgumentType
    data object NUMBER_OR_TEXT : ArgumentType
}