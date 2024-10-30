package de.jensklingenberg.scrako.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable

@Serializable
data class ScratchProject(
    val targets: List<Target>,
    val monitors: List<Monitor> = emptyList(),
    val extensions: List<String> = emptyList(),
    @EncodeDefault val meta: Meta = Meta(
        semver = "3.0.0",
        vm = "0.2.0",
        agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36",
    )
)