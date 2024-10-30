package de.jensklingenberg.scrako.common

import java.util.UUID

class Broadcast(val name: String) {
    val id: UUID = UUID.randomUUID()
}