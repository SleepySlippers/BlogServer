package org.example.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val id: Long,
    val text: String,
    val creationTime: Instant,
    val editTime: Instant,
)
