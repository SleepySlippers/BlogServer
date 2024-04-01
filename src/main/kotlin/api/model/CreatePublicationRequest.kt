package org.example.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePublicationRequest(
    val text: String
)
